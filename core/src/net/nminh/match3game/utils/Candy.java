package net.nminh.match3game.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;

import net.nminh.match3game.Match3Game;

import java.util.HashMap;

public class Candy extends Image implements Disposable
{
    Match3Game game;
    HashMap<Integer, Image> candyMap = new HashMap<>();
    Image candy1,
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
        candy1 = new Image(Assets.getRegion(Consts.COLOR1));
        candy2 = new Image(Assets.getRegion(Consts.COLOR1));
        candy3 = new Image(Assets.getRegion(Consts.COLOR1));
        candy4 = new Image(Assets.getRegion(Consts.COLOR1));
        candy5 = new Image(Assets.getRegion(Consts.COLOR1));
    }

    private void initHashMap()
    {
        candyMap.put(1, candy1);
        candyMap.put(2, candy2);
        candyMap.put(3, candy3);
        candyMap.put(4, candy4);
        candyMap.put(5, candy5);
    }

    @Override
    public void dispose()
    {
        candy1.clear();
        candy2.clear();
        candy3.clear();
        candy4.clear();
        candy5.clear();
    }
}