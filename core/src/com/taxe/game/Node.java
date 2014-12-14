package com.taxe.game;

import com.badlogic.gdx.utils.Json;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    private final String id;

    public Node() {coordinate = null; passable = false; id = null;}

    public Node(Coordinate coordinate, boolean passable) {
        this.coordinate = coordinate;
        this.passable = passable;
        this.id = null;
    }

    public String toString() {
        return "Node <" +
                coordinate.toString() + ", " +
                "passable = " + Boolean.toString(passable) + ", " +
                "id = " + id + ">";
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isPassable() {
        return passable;
    }

    public String getId() {
        return id;
    }

    public static ArrayList<Node> readNodes(String fileName) throws IOException {
        Json json = new Json();
        FileReader f = new FileReader(fileName);
        Node[] nodes = json.fromJson(Node[].class, f);
        f.close();
        return new ArrayList<>(Arrays.asList(nodes));
    }

}
