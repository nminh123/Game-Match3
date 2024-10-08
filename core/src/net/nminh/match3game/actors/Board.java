package net.nminh.match3game.actors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Tile;

public class Board extends Group implements Disposable
{
    Match3Game game;
    Array<TextureAtlas.AtlasRegion> entities;
    Tile[][] tiles = new Tile[8][8];

    public Board(Array<TextureAtlas.AtlasRegion> sprites, int row, int col, int size, Vector2 position)
    {
        this.entities = sprites;

        initialize(row, col, size, position);
    }
    
    public Board(Match3Game game, Array<TextureAtlas.AtlasRegion> sprites)
    {
        this.game = game;
        this.entities = sprites;

        initialize();
    }
    
    private void initialize()
    {
        setBounds(0,0,640,640);
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                Tile tile = new Tile(i ,j);
                tile.addListener(clickListener);
                int num = MathUtils.random(1,4);
                tile.init(this.entities.get(num),num);
//                tile.setPosition(j * tile.getWidth(), i * tile.getHeight());
                tile.setPosition(90, 220, Align.center);
                tile.setSize(Consts.SIZE,Consts.SIZE);
                tiles[i][j] = tile;
                this.addActor(tile);

                if(tiles[i][j] == null)
                    Gdx.app.log("Board", "Could not found Board Texture");
            }
        }
    }

    private void initialize(int row, int col, int size, Vector2 position)
    {
        setBounds(0,0,size, size);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Tile tile = new Tile(i, j);
                tile.addListener(clickListener);
                int num = MathUtils.random(1, 4);
                float x = position.x + col * size;
                float y = position.y + row * size;
                tile.init(this.entities.get(num), num);
                tile.setSize(size,size);
                tile.setPosition(x, y);
                tiles[i][j] = tile;

                this.addActor(tile);
            }
        }
    }

    ClickListener clickListener = new ClickListener()
    {
        Tile firstClick;
        int count = 0;

        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            Tile target = (Tile) event.getTarget();

            if (firstClick != null)
            {
                target.clearActions();
                firstClick.clearActions();

                if (target.row == firstClick.row)
                {
                    if (target.col == firstClick.col + 1 || target.col == firstClick.col - 1)
                    {

                        System.out.println("swap row target <> firstclick");
                        int row = target.row;
                        int col = target.col;
                        tiles[target.col][target.row] = firstClick;
                        tiles[firstClick.col][firstClick.row] = target;
                        target.setRowCol(firstClick.row, firstClick.col);
                        firstClick.setRowCol(row, col);
                        firstClick.addAction(Actions.moveTo(firstClick.row * firstClick.getWidth(), firstClick.col * firstClick.getHeight(), 2));
                        target.addAction(Actions.moveTo(target.row * target.getWidth(), target.col * target.getHeight(), 2));
                    }
                }
                else if (target.col == firstClick.col)
                {
                    if (target.row == firstClick.row + 1 || target.row == firstClick.row - 1)
                    {
                        System.out.println("swap col target <> firstclick");
                        int row = target.row;
                        int col = target.col;
                        tiles[target.col][target.row] = firstClick;
                        tiles[firstClick.col][firstClick.row] = target;
                        target.setRowCol(firstClick.row, firstClick.col);
                        firstClick.setRowCol(row, col);
                        firstClick.addAction(Actions.moveTo(firstClick.row * firstClick.getWidth(), firstClick.col * firstClick.getHeight(), 2));
                        target.addAction(Actions.moveTo(target.row * target.getWidth(), target.col * target.getHeight(), 2));
                    }
                }
            }

            count++;
            firstClick = target;

            if (count == 2)
            {
                firstClick = null;
                count = 0;
            }
            else
            {
                for (Tile[] tile : tiles)
                {
                    for (Tile t : tile)
                    {
                        t.setTouchable(Touchable.disabled);
                    }
                }
                target.setOrigin(Align.center);
                target.addAction(sequence(parallel(moveTo(target.getX(), target.getY() + 10, 0.125f, Interpolation.swingOut),
                                Actions.scaleBy(0.2f, 0.2f, 0.125f)),
                        parallel(Actions.scaleBy(-0.2f, -0.2f, 0.125f),
                                moveTo(target.getX(), target.getY(), 0.125f, Interpolation.swingIn)), afterClick));
            }
        }

        final Action afterClick = new Action() {
            @Override
            public boolean act(float delta) {
                for (Tile[] tile : tiles) {
                    for (Tile t : tile) {
                        t.setTouchable(Touchable.enabled);
                    }
                }
                return true;
            }
        };
    };

    @Override
    public void dispose()
    {
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                tiles[i][j].clear();
            }
        }
    }
}