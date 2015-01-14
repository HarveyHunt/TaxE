package com.taxe.game.Util;

/**
 * Created by Owen on 18/11/2014.
 */
public final class Coordinate {

    private final float x;
    private final float y;

    public Coordinate() {
        this(0, 0);
    }

    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static float distanceBetween(Coordinate cA, Coordinate cB) {
        float dX = cB.x - cA.x;
        float dY = cB.y - cA.y;
        return (float) Math.sqrt(dX * dX + dY * dY);
    }

    public static Coordinate coordinateAlongLine(Coordinate cA, Coordinate cB, float percentage) {
        // Returns the point a certain percentage along a line between nodes 1 and 2
        // percentage as a decimal between 0 and 1.
        float dX = cB.x - cA.x;
        float dY = cB.y - cA.y;
        return new Coordinate(cA.x + dX * percentage, cA.y + dY * percentage);
    }

    public static float angleBetween(Coordinate cA, Coordinate cB) {
        return (float) Math.atan2(cB.y - cA.y, cB.x - cA.x);
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}