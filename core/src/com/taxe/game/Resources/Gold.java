package com.taxe.game.Resources;

/**
 * Created by Owen on 18/11/2014.
 * Class representing players' Gold resource in the game.
 */
public class Gold {

    private int quantity;

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
