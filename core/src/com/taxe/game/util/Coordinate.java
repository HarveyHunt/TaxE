package com.taxe.game.util;

/**
 * Class storing coordinates - a pair of two numbers.
 */
public final class Coordinate {

    private final float x;
    private final float y;

    /**
     * Default constructor.
     */
    public Coordinate() {
        this(0, 0);
    }

    /**
     * Creates instance of Coordinate with set x and y.
     * @param x x value.
     * @param y y value.
     */
    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates distance between two points.
     * @param cA first point.
     * @param cB second point.
     * @return distance between first and second point.
     */
    public static float distanceBetween(Coordinate cA, Coordinate cB) {
        float dX = cB.x - cA.x;
        float dY = cB.y - cA.y;
        return (float) Math.sqrt(dX * dX + dY * dY);
    }

    /**
     * Returns the point a certain percentage along a line between two given points.
     * @param cA first point.
     * @param cB second point.
     * @param percentage how far away from a the resulting point should be; must be in range 0..1.
     * @return a point with coordinates (cA.x + (cB.x - cA.x) * percentage, cA.y + (cB.y - cA.y) * percentage).1
     * @throws IllegalArgumentException if percentage is < 0 or percentage > 1.
     */
    public static Coordinate coordinateAlongLine(Coordinate cA, Coordinate cB, float percentage) throws IllegalArgumentException {
        if (percentage < 0 || percentage > 1)
            throw new IllegalArgumentException("percentage must be in range 0..1");
        float dX = cB.x - cA.x;
        float dY = cB.y - cA.y;
        return new Coordinate(cA.x + dX * percentage, cA.y + dY * percentage);
    }

    /**
     * Return angle between x-axis and line going through two points.
     * @param cA first point.
     * @param cB second point.
     * @return angle between x-axis and line (cA -> cB).
     */
    public static float angleBetween(Coordinate cA, Coordinate cB) {
        return (float) Math.atan2(cB.y - cA.y, cB.x - cA.x);
    }

    /**
     * Verifies if coordinate and another object for equality.
     * @param other object that needs comparing.
     * @return true if other object is instance of Coordinate and x and y values are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Coordinate))
            return false;
        Coordinate c = (Coordinate) other;
        return (x == c.getX() && y == c.getY());
    }

    /**
     * Returns value of x-coordinate
     * @return x-value
     */
    public float getX() {
        return x;
    }

    /**
     * Returns value of y-coordinate
     * @return y-value
     */
    public float getY() {
        return y;
    }

}