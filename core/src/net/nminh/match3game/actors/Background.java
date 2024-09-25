package net.nminh.match3game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.utils.Consts;

import java.util.ArrayList;
import java.util.List;

public class Background extends Actor implements Disposable
{
     int row, col, size;

    Vector2 position;

    TextureRegion
            block1,
            block2,
            GS_BG;

    List<Sprite>[][] board;
    Sprite sprite;

    public Background(int row, int col, int size, Vector2 position)
    {
        this.row = row;
        this.col = col;
        this.size = size;
        this.position = position;

        board = new ArrayList[row][col];

        block1 = Assets.getRegion(Consts.BLOCK_1);
        block2 = Assets.getRegion(Consts.BLOCK_2);
        GS_BG = Assets.getRegion(Consts.GAMESCREEN_BG);
        sprite = new Sprite(GS_BG);
        init();
    }

    private void init()
    {
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                board[i][j] = new ArrayList<>();

                TextureRegion currentRegion = (i + j) % 2 == 0 ? block1 : block2;
                Sprite sprite = new Sprite(currentRegion);
                sprite.setSize(size, size);
                float x = position.x + j * size;
                float y = position.y + i * size;
                sprite.setPosition(x, y);

                board[i][j].add(sprite);
            }
        }
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
        sprite.draw(batch);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                for(Sprite sprite : board[i][j])
                {
                    sprite.draw(batch);
                }
            }
        }
        batch.end();
    }

    @Override
    public void dispose()
    {
        block1.getTexture().dispose();
        block2.getTexture().dispose();
        GS_BG.getTexture().dispose();
    }
}