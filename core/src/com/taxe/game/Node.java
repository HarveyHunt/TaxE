package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vlad on 22/11/14.
 * Class representing Nodes in the game.
 * Nodes are locations of Cities, Homebases, Junctions and Intermediate Points.
 * For each Node, only one instance exists in the game.
 * Nodes are specified and read from JSON file.
 */
public abstract class Node {

    private final Coordinate coordinate;
    private boolean passable;
    private final String id;
    private Texture texture;

    public Node() {
        coordinate = null;
        passable = false;
        id = null;
        texture = null;
    }

    public Node(Texture texture) {
        this();
        this.texture = texture;
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

    public Texture getTexture() {
        return texture;
    }

    public static Node getNodeWithId(String id, Collection<Node> nodes) {
        for (Node n: nodes) {
            System.out.println(n.id);
            if (n.id.equals(id))
                return n;
        }
        return null;
    }

    public static ArrayList<Node> readNodes(String fileName) throws IOException {
        Json json = new Json();
        FileReader f = new FileReader(fileName);
        Node[] nodes = json.fromJson(Node[].class, f);
        f.close();
        return new ArrayList<>(Arrays.asList(nodes));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, (float)coordinate.getX(), (float)coordinate.getY());
    }

}
