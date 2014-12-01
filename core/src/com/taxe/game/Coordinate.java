package com.taxe.game;

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

    private final float x;
    private final float y;

    public Coordinate(float x, float y) {
        // Do we need any checking for invalid coordinates?
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
        // Default Coordinate (0, 0)
        this(0, 0);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public static float distanceBetween(Coordinate coordinate0, Coordinate coordinate1){
        float dX = coordinate1.x - coordinate0.x;
        float dY = coordinate1.y - coordinate0.y;
        return (float)Math.sqrt(dX * dX + dY * dY);
    }

    public static float angleBetween(Coordinate coordinate0, Coordinate coordinate1){
        return (float)Math.atan2(coordinate1.y - coordinate0.y, coordinate1.x - coordinate0.x);
    }


}
