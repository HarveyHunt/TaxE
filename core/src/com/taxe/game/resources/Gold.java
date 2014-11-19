package com.taxe.game.resources;

/**
 * Created by Owen on 18/11/2014.
 */
public class Gold {

    private int quantity;

    public Gold(int quantity) {
        // Check for negative values?
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
