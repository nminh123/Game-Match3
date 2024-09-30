package net.nminh.match3game.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.utils.Candy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends Actor implements Disposable
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
                        break;
                    case 1:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(2)));
                        break;
                    case 2:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(3)));
                        break;
                    case 3:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(4)));
                        break;
                    case 4:
                        image = new Image(new TextureRegionDrawable(block.getCandyImage(5)));
                        break;
                }
                candyImage[i][j].add(image);
                image.setSize(size, size);
                image.setPosition(x, y);
                image.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                    {
                        System.out.println("Board.touchDown");
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
                    {
                        System.out.println("Board.touchUp");
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        }
    }

    public boolean CheckMatches()
    {
        boolean isMatched = false;
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(candyImage[i][j] == candyImage[i+1][j] && candyImage[i][j] == candyImage[i+2][j] ||
                candyImage[i][j] == candyImage[i][j+1] && candyImage[i][j] == candyImage[i][j+2])
                {
                    isMatched = true;
                }
            }
        }
        return isMatched;
    }

    public void RemoveMatchedCandy(int row, int col)
    {
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                candyImage[i][j].clear();
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