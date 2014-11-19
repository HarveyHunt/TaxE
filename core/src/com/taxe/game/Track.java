package com.taxe.game;

import java.util.ArrayList;

/**
 * Created by Owen on 18/11/2014.
 */
public class Track {

    private ArrayList <Coordinate> nodes;

    public Track(){
        nodes = new ArrayList<>();

        // This will all depend on how we decide to implement track
        // Example of adding coordinate to the end of the list.
        nodes.add(new Coordinate(100, 200));
    }

}
