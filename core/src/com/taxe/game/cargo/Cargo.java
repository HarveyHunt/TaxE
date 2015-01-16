package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Base-class for representing cargo. Different types of cargo (e.g. penguins, bricks, etc.) are implemented by
 * extending this class. Cargo is characterised by its quantity (must be non-negative) and has a unique id and texture
 * common to all instances of that cargo type. Texture of each type of cargo is specified in {@link com.taxe.game.cargo.CargoTextures}.
 */
public abstract class Cargo extends Actor {
    private int quantity;

    /**
     * Creates instance of cargo with specified quantity
     *
     * @param quantity initial quantity of cargo
     */
    public Cargo(int quantity) {
        this.quantity = quantity;
        validateQuantity();
    }

    /**
     * Returns quantity of cargo
     *
     * @return quantity of cargo
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates quantity of cargo
     *
     * @param quantity new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        validateQuantity();
    }

    /**
     * Returns id representing cargo type
     *
     * @return id of cargo
     */
    public abstract String getId();

    /**
     * Returns texture representing cargo type
     *
     * @return texture of cargo
     */
    public abstract Texture getTexture();


    /**
     * Checks if quantity of cargo satisfies constraints.
     * @throws AssertionError if quantity < 0
     */
    private void validateQuantity() throws AssertionError {
        assert quantity >= 0;
    }
}



