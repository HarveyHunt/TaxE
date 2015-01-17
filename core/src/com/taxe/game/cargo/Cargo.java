package com.taxe.game.cargo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Base-class for representing cargo. Different types of cargo (e.g. penguins, bricks, etc.) are implemented by
 * extending this class.
 * <p>
 * Cargo is characterised by its quantity, id and texture. Quantity must always be non-negative. Id and texture are
 * specified in the extending class. Textures of each cargo type can be found in {@link
 * com.taxe.game.cargo.CargoTextures}.
 */
public abstract class Cargo extends Actor {
    private int quantity;
    private final String id;

    /**
     * Creates an instance of cargo with specified quantity.
     *
     * @param quantity initial quantity of cargo, must be >= 0.
     */
    public Cargo(int quantity, String id) {
        this.quantity = quantity;
        this.id = id;
        validateQuantity();
    }

    /**
     * Returns quantity of cargo.
     *
     * @return quantity of cargo.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates quantity of cargo.
     *
     * @param quantity new quantity, must be >= 0.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        validateQuantity();
    }

    /**
     * Returns id representing cargo type.
     *
     * @return id of cargo.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns texture representing cargo type.
     *
     * @return texture of cargo.
     */
    public abstract Texture getTexture();

    /**
     * Checks if quantity of cargo satisfies constraints.
     *
     * @throws RuntimeException if quantity < 0.
     */
    private void validateQuantity() throws RuntimeException {
        if (quantity < 0)
            throw new RuntimeException("quantity < 0");
    }
}



