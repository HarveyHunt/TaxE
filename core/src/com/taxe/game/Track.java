package com.taxe.game;

import java.util.ArrayList;

/**
 * Created by Owen on 18/11/2014.
 */
public class Track {

    private ArrayList nodes;

    public Track(int i){
        nodes = new ArrayList(i);

        // This will all depend on how we decide to implement track

        nodes.add(new Coordinate(100, 200)); // Example of adding coordinate to the end of the list.
    }



}
