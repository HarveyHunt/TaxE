package com.taxe.game.nodes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Json;
import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;
import com.taxe.game.inputhandling.Clickable;
import com.taxe.game.util.Coordinate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Base-class for representing nodes in the game. Nodes are places player can interact with, e.g. build trains, trade,
 * gain influence, complete tasks, select path, etc. Nodes can be passable or not passable, meaning that trains are or
 * aren't allowed to travel through them. Nodes can have different states (e.g Selected, Highlighted, etc.) that,
 * depending on type of the node, can affect the texture of the node or can lead to different interactions with them.
 * States are specified in {@link com.taxe.game.nodes.NodeStates}. Texture of each type of node is specified in {@link
 * com.taxe.game.nodes.NodeTextures}.
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
     *
     * @param fileName name of the json-file holding descriptions of nodes.
     * @return list of nodes.
     * @throws IOException      if something went wrong with reading.
     * @throws RuntimeException if json-file has several nodes with same id.
     */
    public static List<Node> readNodes(String fileName) throws IOException, RuntimeException {
        Json json = new Json();
        FileHandle f = Gdx.files.classpath(fileName);
        Node[] nodes = json.fromJson(Node[].class, f);
        for (Node n : nodes) {
            // Checking if there are nodes with the same id
            for (Node t : nodes)
                if (t != n && t.getId().equals(n.getId()))
                    throw new RuntimeException("two or more nodes have same ids");
            n.setState(NodeStates.ORIGINAL);
            n.validate();
        }
        return Arrays.asList(nodes);
    }

    /**
     * Searches for node with given id in a list.
     *
     * @param id    id of a node we're looking for
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
     * Returns x and y coordinate of a node.
     *
     * @return Coordinate(x, y)
     */
    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    /**
     * Checks if node is passable
     *
     * @return true if node is passable, false otherwise
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * Returns unique id of a node.
     *
     * @return id of a node.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns state of a node
     *
     * @return state of a node
     */
    public int getState() {
        return state;
    }

    /**
     * Sets a new state of a node.
     *
     * @param state new state of a node
     */
    public void setState(int state) {
        this.state = state;
        adjustActor();
    }

    /**
     * Returns texture representing node type at the current state.
     *
     * @return texture of node.
     */
    public abstract Texture getTexture();

    /**
     * Adjust actor-properties of a node. This function is called internally following the change of state.
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
            Commands.continuePathCommand.executeCommand(game, this);
        } else if (getState() == NodeStates.SELECTED) {
            Commands.undoPathCommand.executeCommand(game, this);
        } else if (game.hand.isBlockCardActive()) {
            Commands.lockCityCommand.executeCommand(game, this);
            game.hand.setBlockCardState(false);
        }
    }

    /**
     * Validates state of node's variable. By default, Node is always valid. However, some subclasses of Node do require
     * overriding this method.
     *
     * @throws RuntimeException if one or more node's variables is invalid.
     */
    protected void validate() throws RuntimeException {
    }

    /**
     * Represent this node as a string.
     * @return A string representing this node.
     */
    public String toString() {
        return getId();
    }

}
