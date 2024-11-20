package net.nminh.match3game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.actors.Board;
import net.nminh.match3game.actors.FramesPerSecond;
import net.nminh.match3game.actors.Grid;
import net.nminh.match3game.actors.spine.FarmGirl;
import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.actors.Background;

public class GameScreen extends ParentScreen
{
    Match3Game game;
    Background bg;
    Board board;
    Grid grid;
    FramesPerSecond fps;
    Image restart_btn;
//    FarmGirl farmGirl;

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

        setUpBG();
        setUpGrid();
        setUpBoard();
        setUpFPS();
        Restart();
//        setUFarmGirl();
    }

    private void setUpGrid()
    {
        grid = new Grid(Assets.getTexture().findRegions("block"));
        this.addActor(grid);
    }
    
    private void setUpBoard()
    {
        board = new Board(Assets.getTexture().findRegions("color"));
//        board = new Board(Assets.getTexture().findRegions("color"), Consts.ROW, Consts.COL, Consts.SIZE, Consts.POSITION);
        this.addActor(board);
    }

//    private void setUFarmGirl()
//    {
//        farmGirl = new FarmGirl();
//        this.addActor(farmGirl);
//    }

    private void setUpBG()
    {
        bg = new Background(Assets.getRegion(Consts.GAMESCREEN_BG));
        bg.debugAll();
        this.addActor(bg);
    }


    private void Restart()
    {
        restart_btn = new Image(Assets.getRegion(Consts.RESTART_BTN));
        restart_btn.setPosition(0, 590);
        restart_btn.setSize(50, 50);
        restart_btn.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new GameScreen(game));
            }
        });
        this.addActor(restart_btn);
    }

    private void setUpFPS()
    {
        Rectangle fpsBounds = new Rectangle(getCamera().viewportWidth * -320 / -64,
                getCamera().viewportHeight * 1.04f, getCamera().viewportWidth,
                getCamera().viewportHeight);
        fps = new FramesPerSecond(fpsBounds);
        this.addActor(fps);
    }

//    private void setUpFarmGirl()
//    {
//        farmGirl = new FarmGirl();
//        farmGirl.debug();
//        this.addActor(farmGirl);
//    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height)
    {
        getViewport().update(width, height, true);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        board.dispose();
        bg.dispose();
//        farmGirl.dispose();
    }
}