package net.nminh.match3game.screens;

import static com.badlogic.gdx.Gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.nminh.match3game.Match3Game;

public abstract class ParentScreen extends Stage implements Screen
{
    Match3Game game;
    OrthographicCamera cam;

    public ParentScreen(Match3Game game)
    {
        this.game = game;
    }

    @Override
    public void show()
    {
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.update();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0,0,0,1);
        act(delta);
        game.batch.setProjectionMatrix(cam.combined);
        cam.update();

        game.batch.begin();
        draw();
        game.batch.end();

        if(input.isKeyPressed(Input.Keys.SPACE) || input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void dispose()
    {
        super.dispose();
        game.batch.dispose();
    }
}