package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.cargo.CargoTextures;

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
