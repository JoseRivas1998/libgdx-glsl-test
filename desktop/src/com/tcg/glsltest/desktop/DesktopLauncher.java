package com.tcg.glsltest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tcg.glsltest.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Game.WIDTH;
		config.height = Game.HEIGHT;
		new LwjglApplication(new Game(), config);
	}
}
