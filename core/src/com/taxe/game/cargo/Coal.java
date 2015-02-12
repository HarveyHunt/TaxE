package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents coal as a cargo.
 */
public class Coal extends Cargo {

    public Coal(int quantity) {
        super(quantity, "Coal");
    }

    public Texture getTexture() {
        return CargoTextures.COAL;
    }

}