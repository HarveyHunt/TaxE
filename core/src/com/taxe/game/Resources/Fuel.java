package com.taxe.game.Resources;

/**
 * Represents players fuel resource.
 * Having trains takes up some fuel, and player can only build train if building it won't exceed fuelCap.
 */
public class Fuel {

    private int fuelCap;
    private int usedFuel;

    public Fuel(int fuelCap, int usedFuel) {
        this.fuelCap = fuelCap;
        this.usedFuel = usedFuel;
    }

    public int getFuelCap() {
        return fuelCap;
    }

    public int getUsedFuel() {
        return usedFuel;
    }

    public void changeFuelCapBy(int delta) {
        fuelCap += delta;
    }

    public void changeUsedFuelBy(int delta) {
        usedFuel += delta;
    }

    public boolean fuelCapExceeded() {
        return usedFuel > fuelCap;
    }

}
