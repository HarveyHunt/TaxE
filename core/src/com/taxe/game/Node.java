package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Vlad on 22/11/14.
 * Class representing Nodes in the game.
 * Nodes are locations of Cities, Homebases, Junctions and Intermediate Points.
 * For each Node, only one instance exists in the game.
 * Nodes are specified and read from JSON file.
 */
public abstract class Node extends Actor {

    private final Coordinate coordinate;
    private boolean passable;
    private final String id;

    private Texture[] textures;
    private int currentTexture;

    public Node() {
        coordinate = null;
        passable = false;
        id = null;
        textures = null;
        currentTexture = -1;
    }

    public Node(Texture[] textures) {
        this();
        this.textures = textures;
        this.currentTexture = Textures.ORIGINAL;
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

        for (Node n : nodes) {
            n.prepareActor();
        }
        return new ArrayList<>(Arrays.asList(nodes));
    }

    public void prepareActor() {
        setBounds((float) coordinate.getX(), (float) coordinate.getY(),
                textures[currentTexture].getWidth(), textures[currentTexture].getHeight());
        setTouchable(Touchable.enabled);
    }

    public int getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(int texture) {
        currentTexture = texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = (float) coordinate.getX();
        float y = (float) coordinate.getY();
        batch.draw(textures[currentTexture], x, y);
    }

}
