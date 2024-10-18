package net.nminh.match3game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;

public class FramesPerSecond extends Actor implements Disposable
{
    BitmapFont font;
    Rectangle bounds;
    float frameCounter = 0;
    float elapsedTime = 0;
    int fps;

    public FramesPerSecond(Rectangle bounds)
    {
        this.bounds = bounds;
        font = new BitmapFont();
    }
    public int getFps()
    {
        float delta = Gdx.graphics.getDeltaTime();
        elapsedTime += delta;
        frameCounter++;

        if (elapsedTime >= 1.0f) {
            fps = (int) frameCounter;
            frameCounter = 0;
            elapsedTime = 0;
        }
        return fps;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        String fpsText = String.format("Fps: " + getFps());

        GlyphLayout layout = new GlyphLayout();

        layout.setText(font,
                fpsText,
                Color.CYAN,
                bounds.width * 10,
                Align.topLeft,
                true);
        font.draw(batch, layout, bounds.x/6f, bounds.y/1.1f + layout.height);
    }

    @Override
    public void dispose()
    {
        font.dispose();
        clear();
    }
}