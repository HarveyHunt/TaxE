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
        validateQuantity();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        validateQuantity();
    }

    public String getId() {
        return id;
    }

    public Texture getTexture() {
        return texture;
    }

    private void validateQuantity() throws RuntimeException {
        if (quantity < 0)
            throw new RuntimeException("quantity must be non-negative");
    }
}



