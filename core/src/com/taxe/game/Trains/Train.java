package com.taxe.game.Trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.Cargo.Cargo;
import com.taxe.game.Commands.ResetPathCommand;
import com.taxe.game.Commands.StartPathCommand;
import com.taxe.game.Tracks.Sleeper;
import com.taxe.game.Util.Coordinate;
import com.taxe.game.GameCore;
import com.taxe.game.InputHandling.Clickable;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Util.Textures;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by vlad on 10/01/15.
 */
public abstract class Train extends Actor implements Clickable {

    private final String id;
    private int speed;
    private int cargoCap;
    private int fuelCost;
    private Cargo cargo;
    private ArrayDeque<Node> pathNodes;
    private ArrayDeque<Sleeper> pathCoordinates;
    private Node node;

    private ArrayList<Texture> textures;
    private int state;

    public Train(int speed, int cargoCap, int fuelCost, String id, Node node, Texture[] textures) {
        this.speed = speed;
        this.cargoCap = cargoCap;
        this.fuelCost = fuelCost;
        this.id = id;
        this.cargo = null;
        this.pathNodes = new ArrayDeque<>();
        this.pathCoordinates = new ArrayDeque<>();
        this.node = node;
        this.textures = new ArrayList<>(Arrays.asList(textures));
        this.setPosition(node.getX(), node.getY());
        this.setState(Textures.HIGHLIGHTED);
    }

    public int getSpeed() {
        return speed;
    }

    public int getFuelCost() {
        return fuelCost;
    }

    public String getId() {
        return id;
    }

    public int getCargoCap() {
        return cargoCap;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Deque<Node> getPathNodes() {
        return pathNodes;
    }

    public Deque<Sleeper> getPathCoordinates() {
        return pathCoordinates;
    }

    public void setPath(Deque<Node> nodes, Deque<Sleeper> sleepers) {
        this.pathNodes = new ArrayDeque<>(nodes);
        this.pathCoordinates = new ArrayDeque<>(sleepers);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Texture getTexture() {
        return textures.get(state);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        Texture t = getTexture();
        setBounds(getX(), getY(), t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);
        setTouchable(Touchable.enabled);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(getTexture(),
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0, getTexture().getWidth(), getTexture().getHeight(), false, false);
    }

    public void clicked(GameCore game) {
        if (getState() == Textures.HIGHLIGHTED) {
            new StartPathCommand().executeCommand(game, this);
        } else if (getState() == Textures.SELECTED) {
            new ResetPathCommand().executeCommand(game, this);
        }
    }

}
