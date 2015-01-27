package com.taxe.game.cargo.cargotypes;

import com.badlogic.gdx.graphics.Texture;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.cargo.CargoTextures;

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