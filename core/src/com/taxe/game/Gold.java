package com.taxe.game;

/**
 * Created by Owen on 18/11/2014.
 */
public class Gold {

    private int quantity;

    public Gold(int quantity) {
        // Check for negative values?
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public boolean changeQuantity(int delta) {
        // New quantity is negative
        if (quantity + delta < 0) {
            return false;
        }

        // New quantity is non-negative
        quantity += delta;
        return true;
    }

}
