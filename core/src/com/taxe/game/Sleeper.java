package com.taxe.game;

/**
 * Created by Owen on 28/11/2014.
 */
public class Sleeper {

    private Coordinate coordinate;
    private double angle;

    // A sleeper is a horizontal plank on train tracks

    public Sleeper(Coordinate coordinate, double angle) {
        this.coordinate = coordinate;
        this.angle = angle;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getAngle() {
        return angle;
    }

}
