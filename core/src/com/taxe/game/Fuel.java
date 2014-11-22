package com.taxe.game;

/**
 * Created by Vlad on 19/11/2014.
 * Class representing players' Fuel resource in the game.
 */
public class Fuel {

    private int fuelCap;        // Must be always >= 0
    private int usedFuel;       // Must be always >= 0

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
