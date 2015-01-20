package com.taxe.game.util;

/**
 * Stores 2D-coordinates and defines methods for operating them.
 */
public final class Coordinate {

    private final float x;
    private final float y;

    @Deprecated
    public Coordinate() {
        this(0, 0);
    }

    /**
     * Creates instance of Coordinate with set x and y.
     *
     * @param x x value.
     * @param y y value.
     */
    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates distance between two points.
     *
     * @param c0 first point.
     * @param c1 second point.
     * @return distance between first and second point.
     */
    public static float distanceBetween(Coordinate c0, Coordinate c1) {
        float dx = c1.x - c0.x;
        float dy = c1.y - c0.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the point a certain percentage along a line between two given points.
     *
     * @param c0         first point.
     * @param c1         second point.
     * @param percentage how far away from c0 the resulting point should be, must be in range 0..1.
     * @return a point with coordinates (cA.x + (cB.x - cA.x) * percentage, cA.y + (cB.y - cA.y) * percentage).
     * @throws IllegalArgumentException if percentage is  not in range 0..1.
     */
    public static Coordinate coordinateAlongLine(Coordinate c0, Coordinate c1, float percentage) throws IllegalArgumentException {
        if (percentage < 0 || percentage > 1)
            throw new IllegalArgumentException("percentage must be in range 0..1");
        float dx = c1.x - c0.x;
        float dy = c1.y - c0.y;
        return new Coordinate(c0.x + dx * percentage, c0.y + dy * percentage);
    }

    /**
     * Return angle between x-axis and line going through two points.
     *
     * @param c0 first point.
     * @param c1 second point.
     * @return value equivalent to Math.atan2(c1.getY() - c0.getY(), c1.getX() - c0.getX())
     */
    public static float angleBetween(Coordinate c0, Coordinate c1) {
        return (float) Math.atan2(c1.y - c0.y, c1.x - c0.x);
    }

    /**
     * Tests coordinate and another object for equality.
     *
     * @param other object that requires comparing.
     * @return Coordinates c0 and c1 are equal if (c0.getX() == c1.getX() and c0.getY() == c1.getY()).
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
     * Returns value of x.
     *
     * @return x.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns value of y
     *
     * @return y
     */
    public float getY() {
        return y;
    }

}