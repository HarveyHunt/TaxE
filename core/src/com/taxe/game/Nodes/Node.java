package com.taxe.game.Nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Json;
import com.taxe.game.Commands.ContinuePathCommand;
import com.taxe.game.Commands.UndoPathCommand;
import com.taxe.game.Util.Coordinate;
import com.taxe.game.GameCore;
import com.taxe.game.InputHandling.Clickable;
import com.taxe.game.Util.Textures;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base-class for representing nodes in the game.
 * Nodes are units on the map player can interact with.
 */
public abstract class Node extends Actor implements Clickable {

    private final String id;
    private boolean passable;
    private ArrayList<Texture> textures;
    protected int state;

    public Node() {
        passable = true;
        id = null;
        textures = null;
        state = -1;
    }

    public Node(Texture[] textures) {
        this();
        this.textures = new ArrayList<>(Arrays.asList(textures));
    }

    public Node(Coordinate coordinate, String id, Texture[] textures) {
        setPosition((float)coordinate.getX(), (float)coordinate.getY());
        this.id = id;
        this.passable = true;
        this.textures = new ArrayList<>(Arrays.asList(textures));
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
            n.setState(Textures.ORIGINAL);
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
        return (getCoordinate().equals(n.getCoordinate()) &&
                id.equals(n.getId()) &&
                passable == n.isPassable() &&
                textures.equals(n.getTextures()) &&
                state == n.getState());
    }

    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    public boolean isPassable() {
        return passable;
    }

    public String getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public abstract void setState(int state);

    public Texture getTexture() {
        return textures.get(state);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        return x >= -getOriginX() && x < getWidth() - getOriginX() && y >= -getOriginY() && y < getHeight() -getOriginY() ? this : null;
    }

    public List<Texture> getTextures() {
        return textures;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(getTexture(),
                getX() - getOriginX(), getY() - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0, getTexture().getWidth(), getTexture().getHeight(), false, false);
    }

    public void clicked(GameCore game) {
        if (getState() == Textures.HIGHLIGHTED) {
            new ContinuePathCommand().executeCommand(game, this);
        } else if (getState() == Textures.SELECTED) {
            new UndoPathCommand().executeCommand(game, this);
        }
    }

}
