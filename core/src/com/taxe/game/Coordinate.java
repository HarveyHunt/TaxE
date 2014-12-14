package com.taxe.game;

import java.util.DoubleSummaryStatistics;

/**
 * Created by Owen on 18/11/2014.
 */
public final class Coordinate {

    /*
    ----------------
    Stores an X and a Y position
    Not sure this needs any explaining really :3
    ----------------
     */

    private final double x;
    private final double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
        this(0.0, 0.0);
    }

    public String toString() {
        return "Coordinate <" +
                "x = " + Double.toString(x) + ", " +
                "y = " + Double.toString(y) + ">";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double distanceBetween(Coordinate cA, Coordinate cB) {
        double dX = cB.x - cA.x;
        double dY = cB.y - cA.y;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public static double angleBetween(Coordinate cA, Coordinate cB) {
        return Math.atan2(cB.y - cA.y, cB.x - cA.x);
    }

}