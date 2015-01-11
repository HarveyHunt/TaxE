package com.taxe.game.Nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Json;
import com.taxe.game.Coordinate;
import com.taxe.game.Textures;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Base-class for representing nodes in the game.
 * Nodes are units on the map player can interact with.
 */
public abstract class Node extends Actor {

    private final Coordinate coordinate;
    private final String id;
    private boolean passable;
    private Texture[] textures;
    private int state;

    public Node() {
        coordinate = null;
        passable = true;
        id = null;
        textures = null;
        state = 0;
    }

    public Node(Texture[] textures) {
        this();
        this.textures = textures;
        this.state = Textures.ORIGINAL;
    }

    public Node(Coordinate coordinate, String id, Texture[] textures) {
        this.coordinate = coordinate;
        this.id = id;
        this.passable = true;
        this.textures = textures;
        this.state = 0;
    }

    public static Node getNodeWithId(String id, List<Node> nodes) {
        for (Node n : nodes) {
            if (n.id.equals(id))
                return n;
        }
        return null;
    }

    public static List<Node> readNodes(String fileName) throws IOException {
        Json json = new Json();
        FileReader f = new FileReader(fileName);
        Node[] nodes = json.fromJson(Node[].class, f);
        f.close();
        for (Node n : nodes) {
            n.prepareActor();
        }
        return Arrays.asList(nodes);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Node))
            return false;
        Node n = (Node) other;
        return (coordinate.equals(n.getCoordinate()) &&
                id.equals(n.getId()) &&
                passable == n.isPassable() &&
                textures == n.getTextures() &&
                state == n.getState());
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

    private void prepareActor() {
        setBounds((float) coordinate.getX(), (float) coordinate.getY(),
                textures[state].getWidth(), textures[state].getHeight());
        setTouchable(Touchable.enabled);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Texture getTexture() {
        return textures[state];
    }

    public Texture[] getTextures() {
        return getTextures();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = (float) coordinate.getX();
        float y = (float) coordinate.getY();
        batch.draw(textures[state], x, y);
    }

}
