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

    public static Coordinate coordinateAlongLine(Coordinate cA, Coordinate cB, double percentage) {
        // Returns the point a certain percentage along a line between nodes 1 and 2
        // percentage as a decimal between 0 and 1.
        double dX = cB.getX() - cA.getX();
        double dY = cB.getY() - cA.getY();
        return new Coordinate(cA.getX() + dX * percentage, cA.getY() + dY * percentage);
    }

    public static double angleBetween(Coordinate cA, Coordinate cB) {
        return Math.atan2(cB.y - cA.y, cB.x - cA.x);
    }

}