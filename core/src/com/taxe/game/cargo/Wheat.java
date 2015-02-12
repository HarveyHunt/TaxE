package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents wheat as a cargo.
 */
public class Wheat extends Cargo {

    public Wheat(int quantity) {
        super(quantity, "Wheat");
    }

    public Texture getTexture() {
        return CargoTextures.WHEAT;
    }

}