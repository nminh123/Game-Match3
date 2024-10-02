package net.nminh.match3game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.utils.Consts;

public class Background extends Actor implements Disposable
{
    Match3Game game;
    TextureRegion texture;
    Image image;

    public Background(Match3Game game)
    {
        this.game = game;

        init();
    }

    private void init()
    {
        texture = Assets.getRegion(Consts.GAMESCREEN_BG);

        image = new Image(texture);
        image.setPosition(0,0);
        image.setSize(getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        if(image != null)
        {
            image.draw(batch, parentAlpha);
            Gdx.app.log("Background", "Background texture found!!");
        }
        else
            Gdx.app.log("Background", "Background texture not found!!");
    }

    @Override
    public void dispose()
    {
        texture.getTexture().dispose();
        image.clear();
    }
}