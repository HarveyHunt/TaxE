package com.taxe.game.nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Json;
import com.taxe.game.Commands.ContinuePathCommand;
import com.taxe.game.Commands.UndoPathCommand;
import com.taxe.game.GameCore;
import com.taxe.game.Util.Coordinate;
import com.taxe.game.inputhandling.Clickable;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Base-class for representing nodes in the game.
 * Nodes are places player can interact with, e.g. build trains, trade, gain influence, complete tasks, select path, etc.
 * Nodes can be passable or not passable, meaning that trains are or aren't allowed to travel through them.
 * Nodes can have different states (e.g Selected, Highlighted, etc.) that, depending on type of the node, can lead to different interactions with them.
 * States are specified in {@link com.taxe.game.nodes.NodeStates}.
 * Texture of each type of node is specified in {@link com.taxe.game.nodes.NodeTextures}.
 */
public abstract class Node extends Actor implements Clickable {

    private final String id;
    private boolean passable;
    private int state;

    /**
     * Default constructor. Necessary for {@link #readNodes(String)}.
     */
    public Node() {
        passable = true;
        id = null;
        state = NodeStates.ORIGINAL;
    }

    /**
     * Reads nodes from json-file.
     * @param fileName name of the json-file holding descriptions of nodes.
     * @return list of nodes.
     * @throws IOException if something went wrong with reading.
     * @throws RuntimeException if json-file has several nodes with same id.
     */
    public static List<Node> readNodes(String fileName) throws IOException, RuntimeException {
        Json json = new Json();
        FileReader f = new FileReader(fileName);
        Node[] nodes = json.fromJson(Node[].class, f);
        f.close();
        for (Node n : nodes) {
            for (Node t: nodes)
                if (t != n && t.equals(n))
                    throw new RuntimeException("Nodes must have unique ids");
            n.setState(NodeStates.ORIGINAL);
        }
        return Arrays.asList(nodes);
    }

    /**
     * Searches for node with given id in a list.
     * @param id id of a node we're looking for
     * @param nodes list of nodes
     * @return node with given id if such node exists; null otherwise.
     */
    public static Node getNodeById(String id, List<Node> nodes) {
        for (Node n : nodes)
            if (n.getId().equals(id))
                return n;
        return null;
    }

    /**
     * Compares two nodes for equality. Nodes are considered equal if they have the same id.
     * @param other object to be compared
     * @return true if other is instance of Node and ids are equal
     */
    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Node && id.equals(((Node) other).getId()));
    }

    /**
     * Returns x and y coordinate of a node.
     * @return Coordinate(x, y)
     */
    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    /**
     * Checks if node is passable
     * @return true if node is passable, false otherwise
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * Returns unique id of a node.
     * @return id of a node.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns state of a node
     * @return state of a node
     */
    public int getState() {
        return state;
    }

    /**
     * Sets a new state of a node.
     * @param state new state of a node
     */
    public void setState(int state) {
        this.state = state;
        adjustActor();
    }

    /**
     * Returns texture representing cargo type.
     * @return texture of cargo
     */
    public abstract Texture getTexture();

    /**
     * Adjust actor-properties of a node following changes in state.
     */
    protected abstract void adjustActor();

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        return x >= -getOriginX() && x < getWidth() - getOriginX() && y >= -getOriginY() && y < getHeight() - getOriginY() ? this : null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(getTexture(),
                getX() - getOriginX(), getY() - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0, getTexture().getWidth(), getTexture().getHeight(),
                false, false);
    }

    public void clicked(GameCore game) {
        if (getState() == NodeStates.HIGHLIGHTED) {
            ContinuePathCommand.executeCommand(game, this);
        } else if (getState() == NodeStates.SELECTED) {
            UndoPathCommand.executeCommand(game, this);
        }
    }

}
