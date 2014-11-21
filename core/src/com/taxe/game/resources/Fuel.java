package com.taxe.game.resources;

/**
 * Created by Vladimir on 19/11/2014.
 */
public class Fuel {

    private int fuelCap;
    private int usedFuel;

    public Fuel(int fuelCap, int usedFuel) {
        // Checking for invalid values?
        this.fuelCap = fuelCap;
        this.usedFuel = usedFuel;
    }

    public int getFuelCap() {
        return fuelCap;
    }

    public void setFuelCap(int fuelCap) {
        this.fuelCap = fuelCap;
    }

    public int getUsedFuel() {
        return usedFuel;
    }

    public void setUsedFuel(int usedFuel) {
        this.usedFuel = usedFuel;
    }

    public boolean fuelCapExceeded() {
        return usedFuel > fuelCap;
    }
}
