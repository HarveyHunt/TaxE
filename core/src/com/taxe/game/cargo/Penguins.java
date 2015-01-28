package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.cargo.CargoTextures;

/**
 * Represents penguins as a cargo.
 */
public class Penguins extends Cargo {

    public Penguins(int quantity) {
        super(quantity, "Penguins");
    }

    public Texture getTexture() {
        return CargoTextures.PENGUINS;
    }

}