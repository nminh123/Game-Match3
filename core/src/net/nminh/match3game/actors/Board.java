package net.nminh.match3game.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

    public Board(int row, int col, int size, Vector2 position)
    {
        this.row = row;
        this.col = col;
        this.size = size;
        this.position = position;

        game = new Match3Game();
        block = new Candy();

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
                        image = new Image(block.getCandyImage(1));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                    case 1:
                        image = new Image(block.getCandyImage(2));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                    case 2:
                        image = new Image(block.getCandyImage(3));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                    case 3:
                        image = new Image(block.getCandyImage(4));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                    case 4:
                        image = new Image(block.getCandyImage(5));
                        image.setSize(size, size);
                        image.setPosition(x, y);
                        break;
                }
                candyImage[i][j].add(image);
            }
        }
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                for(Image image : candyImage[i][j])
                {
                    image.draw(batch, 0);
                }
            }
        }
        batch.end();
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