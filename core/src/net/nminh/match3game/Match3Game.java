package net.nminh.match3game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.nminh.match3game.screens.HomeScreen;
import net.nminh.match3game.utils.Assets;
import net.nminh.match3game.utils.Consts;
import net.nminh.match3game.utils.Utils;

public class Match3Game extends Game {
	public SpriteBatch batch;
	public Match3Game()
	{
	}

	@Override
	public void create()
	{
		Assets.Load();
		setScreen(new HomeScreen(this));
		batch = new SpriteBatch();
	}

	@Override
	public void render()
	{
		super.render();
	}

	@Override
	public void dispose()
	{
		super.dispose();
		Assets.dispose();
	}
}