package com.taxe.game.tracks;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vlad on 14/01/15.
 */
public class BasicSleeper extends Sleeper {

    public BasicSleeper(boolean ending) {
        super(ending);
    }

    public Texture getTexture() {
        return SleeperTextures.BASIC_SLEEPER;
    }

}
