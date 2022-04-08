package com.metalball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.metalball.MetalBallApp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1080;
		config.height = 1920;
		config.resizable = false;
		config.fullscreen = false;
		new LwjglApplication(new MetalBallApp(), config);
	}
}
