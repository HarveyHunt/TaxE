package com.taxe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.taxe.Main;

public class DesktopLauncher {

    /**
     * This class just configures a few settings and then sends us over to the Main class.
     * The framework supports mobile games also so it specifies that this is a desktop game
     */
    public static void main(String[] arg) {
        // Config
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "CPH - Trains Across Europe";
        config.width = 800;
        config.height = 600;
        config.resizable = false; // Makes it easier to handle resolution if it cannot be changing all the time
        config.fullscreen = false;
        config.samples = 16;

        // Create the Main class instance
        new LwjglApplication(new Main(), config);
    }
}
