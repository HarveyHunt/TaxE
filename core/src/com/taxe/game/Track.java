package com.taxe.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Owen on 18/11/2014.
 */
public class Track {

    private ArrayList<Node> track;

    public Track(ArrayList<Node> track) {
        this.track = track;
    }

    // returns an ArrayDeque listing nodes from the start node to the end node
    public ArrayDeque<Node> getPath() {
        return new ArrayDeque<Node>(track);
    }

    // returns an ArrayDeque listing nodes from the end node to the start node
    public ArrayDeque<Node> getReversedPath() {
        ArrayList<Node> track = this.track;
        Collections.reverse(track);
        return new ArrayDeque<Node>(track);
    }

    // returns the very first node
    public Node getStartNode() {
        return track.get(0);
    }

    // returns the very last node
    public Node getEndNode() {
        return track.get(track.size() - 1);
    }

    public void draw() {
        // Draw the track
    }


}
