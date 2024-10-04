package net.nminh.match3game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

import net.nminh.match3game.Match3Game;

public class Grid extends Group implements Disposable
{
    Match3Game game;
    TextureRegion block1, block2;
    Image[][] grid = new Image[8][8];

    public Grid(Match3Game game, TextureRegion block1, TextureRegion block2)
    {
        this.game = game;
        this.block1 = block1;
        this.block2 = block2;

        initialize(block1, block2);
    }

    private void initialize(TextureRegion block1, TextureRegion block2)
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                TextureRegion region = (i + j) % 2 == 0 ? block1 : block2;
                Image image = new Image(region);

                grid[i][j] = image;
                image.setSize(getWidth(), getHeight());

                this.addActor(grid[i][j]);
            }
        }
    }

    @Override
    public void dispose()
    {
        block1.getTexture().dispose();
        block2.getTexture().dispose();

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                grid[i][j].clear();
            }
        }
    }
}