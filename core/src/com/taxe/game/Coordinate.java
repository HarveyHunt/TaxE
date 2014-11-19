package com.taxe.game;

/**
 * Created by Owen on 18/11/2014.
 */
public class Coordinate {

    /*
    ----------------
    Stores an X and a Y position
    Not sure this needs any explaining really :3
    ----------------
     */

    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(){
        this(0, 0); // Default coordinate position if x and y aren't defined is (0,0).
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void set(int x, int y){
        this.x = x;
        this.y = y;
    }

}
