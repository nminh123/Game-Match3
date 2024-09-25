package net.nminh.match3game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;

import net.nminh.match3game.Match3Game;

import java.util.HashMap;

public class Candy extends Image implements Disposable
{
    Match3Game game;
    HashMap<Integer, TextureRegion> candyMap = new HashMap<>();
    TextureRegion candy1,
            candy2,
            candy3,
            candy4,
            candy5;

    public Candy()
    {
        game = new Match3Game();
        game.batch = new SpriteBatch();

        init();
        initHashMap();
    }

    private void init()
    {
        candy1 = new TextureRegion(Assets.getRegion(Consts.COLOR1));
        candy2 = new TextureRegion(Assets.getRegion(Consts.COLOR2));
        candy3 = new TextureRegion(Assets.getRegion(Consts.COLOR3));
        candy4 = new TextureRegion(Assets.getRegion(Consts.COLOR4));
        candy5 = new TextureRegion(Assets.getRegion(Consts.COLOR5));
    }

    private void initHashMap()
    {
        candyMap.put(1, candy1);
        candyMap.put(2, candy2);
        candyMap.put(3, candy3);
        candyMap.put(4, candy4);
        candyMap.put(5, candy5);
    }

    public TextureRegion getCandyImage(int id)
    {
        return candyMap.get(id);
    }

    @Override
    public void dispose()
    {
        candy1.getTexture().dispose();
        candy2.getTexture().dispose();
        candy3.getTexture().dispose();
        candy4.getTexture().dispose();
        candy5.getTexture().dispose();
    }
}