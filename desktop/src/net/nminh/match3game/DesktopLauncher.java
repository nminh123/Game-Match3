package net.nminh.match3game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import net.nminh.match3game.utils.Consts;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode((int)Consts.VIEWPORT_WIDTH, (int)Consts.VIEWPORT_HEIGHT);
		config.setWindowIcon(Files.FileType.Internal, "icon.png");
		config.setTitle("Match 3 Game");
		new Lwjgl3Application(new Match3Game(), config);
	}
}
