package com.taxe.game.Cargo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vlad on 14/01/15.
 */
public class Penguins extends Cargo {

    public Penguins(int quantity, String id) {
        super(quantity, id);
    }

    public Texture getTexture() {
        return CargoTextures.PENGUINS;
    }


}
