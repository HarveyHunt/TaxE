package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents penguins as a cargo.
 */
public class Penguins extends Cargo {

    public Penguins(int quantity) {
        super(quantity);
    }

    public Texture getTexture() {
        return CargoTextures.PENGUINS;
    }

    public String getId() {
        return "Penguins";
    }

}
