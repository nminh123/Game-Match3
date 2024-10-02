package net.nminh.match3game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.actors.Background;
import net.nminh.match3game.actors.Grid;
import net.nminh.match3game.actors.Board;
import net.nminh.match3game.utils.Assets;

public class GameScreen extends ParentScreen
{
    Match3Game game;
    Background bg;
    Board board;
    Grid grid;

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
        setUpGrid();
        setUpBoard();
        setUpBG();
    }

    public void setUpViewPort()
    {
        setViewport(new ScreenViewport());
    }

    private void setUpGrid()
    {
        grid = new Grid(game, Assets.getTexture().findRegions("block"));
        grid.setPosition(getWidth()/2, getHeight(), Align.center);
        grid.debug();
        this.addActor(grid);
    }

    private void setUpBG()
    {
        bg = new Background(game);
        addActor(bg);
    }

    private void setUpBoard()
    {
        board = new Board(game, Assets.getTexture().findRegions("color"));
        board.setPosition(getWidth()/2, getHeight(), Align.center);
//        board.debugAll();
        this.addActor(board);
    }

    private void update()
    {
        game.batch.begin();
        grid.draw(game.batch, 1);
        board.draw(game.batch, 1);
        bg.draw(game.batch, 1);
        game.batch.end();
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);

        update();
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
        grid.dispose();
        board.dispose();
    }
}