package com.taxe.game;

/**
 * Created by Owen on 18/11/2014.
 */
public final class Coordinate {

    private final double x;
    private final double y;

    public Coordinate() {
        this(0, 0);
    }

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distanceBetween(Coordinate cA, Coordinate cB) {
        double dX = cB.x - cA.x;
        double dY = cB.y - cA.y;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public static Coordinate coordinateAlongLine(Coordinate cA, Coordinate cB, double percentage) {
        // Returns the point a certain percentage along a line between nodes 1 and 2
        // percentage as a decimal between 0 and 1.
        double dX = cB.x - cA.x;
        double dY = cB.y - cA.y;
        return new Coordinate(cA.x + dX * percentage, cA.y + dY * percentage);
    }

    public static double angleBetween(Coordinate cA, Coordinate cB) {
        return Math.atan2(cB.y - cA.y, cB.x - cA.x);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Coordinate))
            return false;
        Coordinate c = (Coordinate) other;
        return (x == c.getX() && y == c.getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}