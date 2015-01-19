package com.taxe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.taxe.Main;

public class DesktopLauncher {

    /**
     * This class just configures a few settings and then sends us over to the Main class.
     * The framework supports mobile games also so it specifies that this is a desktop game.
     * @param arg
     */
    public static void main(String[] arg) {
        // Config
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Game Titles are Boring";
        config.width = 1480;
        config.height = 890;
        config.resizable = true; // Makes it easier to handle resolution if it cannot be changing all the time
        config.fullscreen = false;
        config.samples = 4;

        // Create the Main class instance
        new LwjglApplication(new Main(), config);
    }
}
