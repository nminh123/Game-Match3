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

import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Tile;

public class Board extends Group implements Disposable {
    Array<TextureAtlas.AtlasRegion> entities;
    Tile[][] tiles = new Tile[8][8];
    Vector2 position;

    public Board(Array<TextureAtlas.AtlasRegion> sprites, int row, int col, int size, Vector2 position) {
        this.entities = sprites;

        initialize(row, col, size, position);
    }

    public Board(Array<TextureAtlas.AtlasRegion> sprites) {
        this.entities = sprites;

        initialize();
    }

    private void initialize() {
        position = Consts.POSITION;
        setBounds(0, 0, 640, 640);
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = new Tile(i, j);
                tile.addListener(clickListener);
                int num = MathUtils.random(1, 4);
                float x = position.x + j * Consts.SIZE;
                float y = position.y + i * Consts.SIZE;
                tile.init(this.entities.get(num), num);
                tile.setPosition(x, y);
                tile.setSize(Consts.SIZE, Consts.SIZE);
                tiles[i][j] = tile;
                this.addActor(tile);

                if (tiles[i][j] == null)
                    Gdx.app.log("Board", "Could not found Board Texture");
            }
        }
    }

    private void initialize(int row, int col, int size, Vector2 position) {
        setBounds(0, 0, 640, 640);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Tile tile = new Tile(i, j);
                tile.addListener(clickListener);
                int num = MathUtils.random(1, 4);
                float x = position.x + col * size;
                float y = position.y + row * size;
                tile.init(this.entities.get(num), num);
                tile.setSize(size, size);
                tile.setPosition(x, y);
                tiles[i][j] = tile;

                this.addActor(tile);
            }
        }
    }

    ClickListener clickListener = new ClickListener() {
        Tile firstClick;
        int count = 0;

        @Override
        public void clicked(InputEvent event, float x, float y) {
            Tile target = (Tile) event.getTarget();

            if (firstClick != null) {
                target.clearActions();
                firstClick.clearActions();

//                System.out.println("First click x: " + firstClick.getX() + "\nFirst click y: " + firstClick.getY());
//                System.out.println("Target click x: " + target.getX() + "\nTarget click y: " + target.getY());
//                System.out.println("Mouse Position x: " + Gdx.input.getX() + "\nMouse Position y: " + Gdx.input.getY());

                if (target.row == firstClick.row) {
                    if (target.col == firstClick.col + 1 || target.col == firstClick.col - 1) {
                        System.out.println("Swap row target <> firstclick");
                        int row = target.row;
                        int col = target.col;
                        tiles[target.col][target.row] = firstClick;
                        tiles[firstClick.col][firstClick.row] = target;

                        target.setRowCol(firstClick.row, firstClick.col);
                        firstClick.setRowCol(row, col);

                        firstClick.addAction(Actions.moveTo(position.x + firstClick.row * Consts.SIZE,
                                position.y + firstClick.col * Consts.SIZE, .15f));
                        target.addAction(Actions.moveTo(position.x + target.row * Consts.SIZE,
                                position.y + target.col * Consts.SIZE, .15f));
                        findMatches();
                    }
                } else if (target.col == firstClick.col) {
                    if (target.row == firstClick.row + 1 || target.row == firstClick.row - 1) {
                        System.out.println("Swap col target <> firstclick");
                        int row = target.row;
                        int col = target.col;
                        tiles[target.col][target.row] = firstClick;
                        tiles[firstClick.col][firstClick.row] = target;

                        target.setRowCol(firstClick.row, firstClick.col);
                        firstClick.setRowCol(row, col);

                        firstClick.addAction(Actions.moveTo(position.x + firstClick.row * Consts.SIZE,
                                position.y + firstClick.col * Consts.SIZE, .15f));
                        target.addAction(Actions.moveTo(position.x + target.row * Consts.SIZE,
                                position.y + target.col * Consts.SIZE, .15f));
                        findMatches();
                    }
                }
            }

            count++;
            firstClick = target;

            if (count == 2) {
                firstClick = null;
                count = 0;
            } else {
                for (Tile[] tile : tiles) {
                    for (Tile t : tile) {
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

    private void findMatches() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                int type = tiles[i][j].getType();
                if (tiles[i][j] != null) {
                    if ((tiles[i + 1][j] != null || tiles[i + 2][j] != null) ||
                            (tiles[i - 1][j] != null || tiles[i - 2][j] != null)) {
                        if ((tiles[i + 1][j].getType() == type && tiles[i + 2][j].getType() == type)) {
                            for (int k = 0; k <= 3; k++) {
                                tiles[i + k][j].addAction(Actions.fadeOut(.3f));
                                tiles[i + k][j].clear();
                            }
                        } else if ((tiles[i - 1][j].getType() == type && tiles[i - 2][j].getType() == type)) {
                            for (int k = 0; k <= 3; k++) {
                                tiles[i - k][j].addAction(Actions.fadeOut(.3f));
                                tiles[i - k][j].clear();
                            }
                        }
                    }
                    if ((tiles[i][j + 1] != null || tiles[i][j + 2] != null) ||
                            (tiles[i][j - 1] != null || tiles[i][j - 2] != null)) {
                        if ((tiles[i][j + 1].getType() == type && tiles[i][j + 2].getType() == type)) {
                            for (int k = 0; k <= 3; k++) {
                                tiles[i][j + k].addAction(Actions.fadeOut(.5f));
                                tiles[i][j + k].clear();
                            }
                        } else if ((tiles[i][j - 1].getType() == type && tiles[i][j - 2].getType() == type)) {
                            for (int k = 0; k <= 3; k++) {
                                tiles[i][j - k].addAction(Actions.fadeOut(.5f));
                                tiles[i][j - k].clear();
                            }
                        }
                    }
                }
            }
        }
    }

    private void FallingTiles(Tile tile1, Tile tile2)
    {
        if (tile2.row == tile1.row)
        {
            if (tile2.col == tile1.col - 1)
            {
                int row = tile1.row;
                int col = tile1.col;

                tile2.setRowCol(row, col);
                tile2.addAction(Actions.moveTo(
                        position.x + tile2.row * Consts.SIZE,
                        position.y + tile2.col * Consts.SIZE,
                        0.15f));
            }
        }
    }

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