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

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        // Do we need any checking for invalid coordinates?
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
        this(0, 0); // Default coordinate position if x and y aren't defined is (0,0).
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
