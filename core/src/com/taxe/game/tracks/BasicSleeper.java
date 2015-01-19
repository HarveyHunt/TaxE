package com.taxe.game.tracks;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class implementing a simple sleeper.
 */
public class BasicSleeper extends Sleeper {

    public BasicSleeper(float x, float y, float rotation, boolean ending) {
        super(x, y, rotation, ending);
    }

    public Texture getTexture() {
        return SleeperTextures.BASIC_SLEEPER;
    }

}
