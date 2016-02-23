package com.gdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.game.KayRahTayMan;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                
                config.width = 640;     //horizontal width of the app window
                config.height = 480;    //vertical height of the app window
                
                //Creates and runs an instance of the kayratayman game
		new LwjglApplication(new KayRahTayMan(), config);  
	}
}
