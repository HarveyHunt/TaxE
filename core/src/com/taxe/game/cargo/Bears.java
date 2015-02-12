package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents penguins as a cargo.
 */
public class Bears extends Cargo {

    public Bears(int quantity) {
        super(quantity, "Bears");
    }

    public Texture getTexture() {
        return CargoTextures.BEARS;
    }

}
