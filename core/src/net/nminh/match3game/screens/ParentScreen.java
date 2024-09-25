package net.nminh.match3game.screens;

import static com.badlogic.gdx.Gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.nminh.match3game.Match3Game;

public abstract class ParentScreen extends Stage implements Screen
{
    Match3Game game;
    OrthographicCamera cam;

    public ParentScreen()
    {
        game = new Match3Game();
        game.batch = new SpriteBatch();
        cam = new OrthographicCamera();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0,0,0,1);
        act(delta);
        draw();
        game.batch.setProjectionMatrix(cam.combined);
        cam.update();

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
        game.batch.dispose();
    }
}