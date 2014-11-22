package com.taxe.game;

/**
 * Created by Vlad on 19/11/2014.
 * Class representing different types of trains.
 * For each different TrainType, only one instance exists in the game.
 * TrainTypes are specified and read from JSON file.
 */
public class TrainType {

    private final int speed;
    private final int cargoCap;
    private final int fuelCost;
    private final String name;

    public TrainType(int speed, int cargoCap, int fuelCost, String name) {
        this.speed = speed;
        this.cargoCap = cargoCap;
        this.fuelCost = fuelCost;
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFuelCost() {
        return fuelCost;
    }

    public String getName() {
        return name;
    }

    public int getCargoCap() {
        return cargoCap;
    }
}
