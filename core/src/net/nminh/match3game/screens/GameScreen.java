package net.nminh.match3game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.actors.Grid;
import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.actors.Board;
import net.nminh.match3game.actors.Background;

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

        setUpGrid();
        setUpBoard();
        setUpBG();
    }

    private void setUpGrid()
    {
        //todo
        grid = new Grid(game, Assets.getRegion(Consts.BLOCK_1), Assets.getRegion(Consts.BLOCK_2));
        grid.setPosition(getWidth()/2, getHeight(), Align.center);
        grid.debugAll();
        this.addActor(grid);
    }

    private void setUpBoard()
    {
        //todo
        board = new Board(game, Assets.getTexture().findRegions("color"));
        board.setPosition(getWidth()/2, getHeight()/5, Align.center);
        board.debugAll();
        this.addActor(board);
    }

    private void setUpBG()
    {
        bg = new Background(game);
        bg.debugAll();
        this.addActor(bg);
    }

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
        grid.dispose();
        board.dispose();
        bg.dispose();
    }
}