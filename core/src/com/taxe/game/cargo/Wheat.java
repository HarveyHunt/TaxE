package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.cargo.CargoTextures;

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