package net.nminh.match3game.actors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.utils.Candy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends Actor implements InputProcessor,Disposable
{
    Match3Game game;
    Candy block;
    int row, col, size, rnumber;
    float x, y;
    Vector2 position;
    Image image;
    Random random = new Random();
    List<Image>[][] candyImage;


    public Board(Match3Game game, int row, int col, int size, Vector2 position)
    {
        this.game = game;
        this.row = row;
        this.col = col;
        this.size = size;
        this.position = position;

        block = new Candy(game);

        candyImage = new ArrayList[row][col];

        init();
    }

    private void init()
    {
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                candyImage[i][j] = new ArrayList<>();

                x = position.x + j * size;
                y = position.y + i * size;
                rnumber = random.nextInt(5);
                switch(rnumber)
                {
                    case 0:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(1)));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                    case 1:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(2)));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                    case 2:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(3)));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                    case 3:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(4)));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                    case 4:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(5)));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                }
                candyImage[i][j].add(image);
            }
        }
    }

    public void draw(SpriteBatch batch, float parentAlpha)
    {
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                for(Image image : candyImage[i][j])
                {
                    image.draw(batch, parentAlpha);
                }
            }
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY)
    {
        return false;
    }

    @Override
    public void dispose()
    {
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                candyImage[i][j].clear();
            }
        }
    }
}