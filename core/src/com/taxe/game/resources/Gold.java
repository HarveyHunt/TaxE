package com.taxe.game.resources;

/**
 * Class representing players gold resource in the game. Gold can be used to buy trains. Gold quantity must always be
 * non-negative.
 */
public class Gold {

    private int quantity;

    /**
     * Creates an instance of Gold with set quantity.
     *
     * @param quantity amount of gold, must be non-negative.
     */
    public Gold(int quantity) {
        this.quantity = quantity;
        validateQuantity();
    }

    /**
     * Returns quantity of gold.
     *
     * @return amount of gold.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Changes quantity of gold by a set amount. If result is negative, it is clipped to 0.
     *
     * @param delta by how much amount of gold is changed.
     */
    public void changeQuantityBy(int delta) {
        quantity = Integer.max(0, quantity + delta);
        validateQuantity();
    }

    /**
     * Checks if quantity of gold satisfies constraints.
     *
     * @throws RuntimeException if quantity < 0.
     */
    private void validateQuantity() throws RuntimeException {
        if (quantity < 0) throw new RuntimeException("quantity < 0");
    }
}
