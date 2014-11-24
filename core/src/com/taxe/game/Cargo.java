package com.taxe.game;
import java.awt.*;

/**
 * Created by Owen on 19/11/2014.
 */
public class Cargo {
    private int quantity;
    private CargoType cargoType;

    public Cargo(int quantity, CargoType cargoType) {
        this.quantity = quantity;
        this.cargoType = cargoType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CargoType getCargoType(){
        return cargoType;
    }
}



