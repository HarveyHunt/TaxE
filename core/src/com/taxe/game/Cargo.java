package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Owen on 19/11/2014.
 */
public class Cargo {
    private int quantity;
    private CargoType cargoType;

    public Cargo(CargoType cargoType, int quantity) {
        this.quantity = quantity;
        this.cargoType = cargoType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CargoType getCargoType() {
        return cargoType;
    }
}


