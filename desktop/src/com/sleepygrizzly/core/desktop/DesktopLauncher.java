package com.sleepygrizzly.core.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sleepygrizzly.core.DrawObject;
import com.sleepygrizzly.core.HomeGame;
import com.sleepygrizzly.core.MainGame;
import com.sleepygrizzly.core.Physics1;
import com.sleepygrizzly.core.Physics2;
import com.sleepygrizzly.core.Physics3;
import com.sleepygrizzly.core.Physics4;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 668;
		new LwjglApplication(new HomeGame(), config);
	}
}
