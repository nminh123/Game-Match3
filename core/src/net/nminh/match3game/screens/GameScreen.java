package net.nminh.match3game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.actors.Background;
import net.nminh.match3game.actors.Board;
import net.nminh.match3game.actors.FramesPerSecond;
import net.nminh.match3game.utils.Consts;

public class GameScreen extends ParentScreen
{
    Match3Game game;
    OrthographicCamera cam;
    FramesPerSecond fps;
    Background gameBG;
    Board board;

    public GameScreen()
    {
        game = new Match3Game();
        cam = new OrthographicCamera();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show()
    {
        super.show();
        game.batch = new SpriteBatch();
        setUpFPS();
        setUpBG();
        setUpBoard();
    }

    private void setUpFPS()
    {
        Rectangle fpsBounds = new Rectangle(getCamera().viewportWidth * -320 / -64,
                getCamera().viewportHeight * 1.04f, getCamera().viewportWidth,
                getCamera().viewportHeight);
        fps = new FramesPerSecond(fpsBounds);
        addActor(fps);
    }

    private void setUpBG()
    {
        gameBG = new Background(Consts.ROW, Consts.COL, Consts.SIZE, Consts.POSITION);
        addActor(gameBG);
    }

    private void setUpBoard()
    {
        board = new Board(Consts.ROW, Consts.COL, Consts.SIZE, Consts.POSITION);
        addActor(board);
    }

    private void update()
    {
        gameBG.draw(game.batch);
        board.draw(game.batch);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);

        update();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        game.batch.dispose();
        fps.dispose();
        gameBG.dispose();
        board.dispose();
    }
}