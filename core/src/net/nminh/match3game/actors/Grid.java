package net.nminh.match3game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;

import net.nminh.match3game.Match3Game;

public class Grid extends Group implements Disposable {
    Match3Game game;
    Image[][] grid = new Image[8][8];

    public Grid(Match3Game game, TextureRegion TextureBlock)
    {
        this.game = game;

        init(TextureBlock);
    }

    private void init(TextureRegion TextureBlock)
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                Image image = new Image(TextureBlock);
                image.setSize(image.getImageWidth(), image.getImageHeight());

                grid[i][j] = image;
                this.addActor(grid[i][j]);
            }
        }
    }

    @Override
    public void dispose()
    {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].clear();
            }
        }
    }
}