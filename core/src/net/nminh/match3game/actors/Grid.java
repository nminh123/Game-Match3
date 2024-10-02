package net.nminh.match3game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.nminh.match3game.utils.Tile;
import net.nminh.match3game.Match3Game;

public class Grid extends Group implements Disposable
{
    Match3Game game;
    Tile[][] grids = new Tile[8][8];
    Array<TextureAtlas.AtlasRegion> textures;

    public Grid(Match3Game game, Array<TextureAtlas.AtlasRegion> textures)
    {
        this.game = game;
        this.textures = textures;

        initialize();
    }

    private void initialize()
    {
        for (int i = 0; i < grids.length; i++)
        {
            for (int j = 0; j < grids[i].length; j++)
            {
                Tile grid = new Tile(i,j);
                int rnum = MathUtils.random(0,1);
                grid.init(this.textures.get(rnum), rnum);
                grid.setPosition(j * grid.getWidth(),
                        i * grid.getHeight());
                this.grids[i][j] = grid;
                this.addActor(grid);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        for(Tile grid[] : grids)
        {
            for(Tile g : grid)
            {
                if(g != null)
                {
                    g.draw(batch, parentAlpha);
                    Gdx.app.log("Board Texture", "Grid's Texture found!!");
                }
                else
                    Gdx.app.log("Board Texture", "Grid's Texture not found!!");
            }
        }
    }

    @Override
    public void dispose()
    {
        for(int i = 0; i < grids.length; i++)
        {
            for(int j = 0; j < grids[i].length; j++)
            {
                grids[i][j].clear();
            }
        }
    }
}