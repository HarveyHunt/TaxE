package com.taxe.game;

/**
 * Created by Owen on 18/11/2014.
 */
public class Gold {

    private int quantity;

    public Gold(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public boolean doTransaction(int delta){
        if (quantity - delta < 0) {
            return false; // Failure, insufficient gold
        }
        quantity -= delta;
        return true; // Success
    }


}
