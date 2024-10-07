package net.nminh.match3game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.nminh.match3game.Match3Game;

public class Background extends Group implements Disposable
{
    Image image;

    public Background(TextureRegion texture)
    {
        init(texture);
    }

    private void init(TextureRegion texture)
    {
        image = new Image(texture);
        image.setPosition(0,0);
        image.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(image == null)
            Gdx.app.log("BackGround", "Could not found background!!");
        this.addActor(image);
    }

    @Override
    public void dispose()
    {
        image.clear();
    }
}