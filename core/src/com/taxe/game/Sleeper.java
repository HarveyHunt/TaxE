package com.taxe.game;

/**
 * Created by Owen on 28/11/2014.
 */
public class Sleeper {

    private Coordinate position;
    private double angle;

    // A sleeper is a horizontal plank on train tracks

    public Sleeper(Coordinate position, double angle){
        this.position = position;
        this.angle = angle;
    }

    public Coordinate getPosition(){
        return position;
    }

    public double getAngle(){
        return angle;
    }

}
