package net.nminh.match3game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.actors.Grid;
import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.actors.Board;
import net.nminh.match3game.actors.Background;

public class GameScreen extends ParentScreen
{
    Match3Game game;
    Background bg;
//    Board board;
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
//        setUpBoard();
        setUpBG();
    }

    private void setUpGrid()
    {
        grid = new Grid(game, Assets.getTexture().findRegion("block", 1),
                Assets.getTexture().findRegion("block", 2));
        grid.setPosition(getWidth()/2, getHeight(), Align.center);
        grid.debugAll();
        this.addActor(grid);
    }


//    private void setUpBoard()
//    {
//        board = new Board(game, Assets.getTexture().findRegions("color"));
//        board.setPosition(getWidth()/2, getHeight(), Align.center);
//        this.addActor(board);
//    }

    private void setUpBG()
    {
        bg = new Background(game);
        addActor(bg);
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
//        grid.dispose();
//        board.dispose();
        bg.dispose();
    }
}