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

    public static float distanceBetween(Coordinate node1, Coordinate node2){
        float dx = node2.x - node1.x;
        float dy = node2.y - node1.y;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    public static float angleBetween(Coordinate node1, Coordinate node2){
        return (float)Math.atan2(node2.y - node1.y, node2.x - node1.x);
    }


}
