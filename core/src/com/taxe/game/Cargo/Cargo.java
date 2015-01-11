package com.taxe.game.Cargo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Base-class for representing cargo in the game.
 * Different types of cargo are implemented by extending this class.
 */
public abstract class Cargo {
    private final String id;
    private final Texture texture;
    private int quantity;

    public Cargo(int quantity, String id, Texture texture) {
        this.quantity = quantity;
        this.id = id;
        this.texture = texture;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Texture getTexture() {
        return texture;
    }
}



