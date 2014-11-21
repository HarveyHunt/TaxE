package com.taxe.game;

import java.util.ArrayList;

/**
 * Created by Owen on 18/11/2014.
 */
public class Track {

    private ArrayList <Coordinate> nodes;

    public Track(ArrayList<Coordinate> nodes){
       this.nodes = nodes;

        // This will all depend on how we decide to implement track
        // Example of adding coordinate to the end of the list.
        nodes.add(new Coordinate(100, 200));
    }

    public void draw(){
        // Draw the track
    }

}
