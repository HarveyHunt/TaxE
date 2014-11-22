package com.taxe.game;

/**
 * Created by Owen on 18/11/2014.
 * Class representing players' Gold resource in the game.
 */
public class Gold {

    private int quantity;           // Must be always >= 0

    public Gold(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void changeQuantityBy(int delta) {
        quantity += delta;
    }
}
