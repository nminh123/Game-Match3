package net.nminh.match3game.screens;

import com.badlogic.gdx.Gdx;

import net.nminh.match3game.Match3Game;
import net.nminh.match3game.actors.Board;
import net.nminh.match3game.actors.Grid;
import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Assets;
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

        setUpBG();
        setUpGrid();
        setUpBoard();
    }

    private void setUpGrid()
    {
        grid = new Grid(Consts.ROW,
                        Consts.COL,
                        Consts.SIZE,
                        Consts.POSITION,
                        Assets.getTexture().findRegion("block",1),
                        Assets.getTexture().findRegion("block", 2));
        grid.debugAll();
        this.addActor(grid);
    }
    
    private void setUpBoard()
    {
        board = new Board(game, Assets.getTexture().findRegions("color"));
//        board = new Board(Assets.getTexture().findRegions("color"), Consts.ROW, Consts.COL, Consts.SIZE, Consts.POSITION);
        board.debugAll();
        this.addActor(board);
    }

    private void setUpBG()
    {
        bg = new Background(Assets.getRegion(Consts.GAMESCREEN_BG));
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
        board.dispose();
        bg.dispose();
    }
}