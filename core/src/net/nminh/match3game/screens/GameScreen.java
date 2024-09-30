package net.nminh.match3game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import net.nminh.match3game.Field;
import net.nminh.match3game.Match3Game;
import net.nminh.match3game.actors.Background;
import net.nminh.match3game.actors.Board;
import net.nminh.match3game.actors.FramesPerSecond;
import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.utils.Consts;

public class GameScreen extends ParentScreen
{
    Match3Game game;
    FramesPerSecond fps;
    Background gameBG;
//    Board board;
    Field field;


    public GameScreen(Match3Game game)
    {
        super(game);
        this.game = game;

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show()
    {
        super.show();

        setUpViewPort();
        setUpFPS();
        setUpBG();
//        setUpBoard();
        setUpField();
    }

    public void setUpViewPort()
    {
        setViewport(new ScreenViewport());
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

//    private void setUpBoard()
//    {
//        board = new Board(game, Consts.ROW, Consts.COL, Consts.SIZE, Consts.POSITION);
//        addActor(board);
//    }

    private void setUpField()
    {
        Table maintable = new Table();
        field = new Field(game, Assets.getTexture().findRegions("color"));
        maintable.add(field);
    }
    private void update()
    {
        game.batch.begin();
        gameBG.draw(game.batch);
//        board.draw(game.batch, 1);
        game.batch.end();
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
//        board.dispose();
    }
}