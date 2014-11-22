package com.taxe.game;

/**
 * Created by Vlad on 22/11/14.
 * Class representing Nodes in the game.
 * Nodes are locations of Cities, Homebases, Junctions and Intermediate Points.
 * For each Node, only one instance exists in the game.
 * Nodes are specified and read from JSON file.
 */
public class Node {

    private final Coordinate coordinate;
    private final boolean passable;

    public Node(Coordinate coordinate, boolean passable) {
        this.coordinate = coordinate;
        this.passable = passable;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isPassable() {
        return passable;
    }

}
