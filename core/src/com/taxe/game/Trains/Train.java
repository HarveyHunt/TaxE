package com.taxe.game.Trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.Cargo.Cargo;
import com.taxe.game.Commands.ResetPathCommand;
import com.taxe.game.Commands.StartPathCommand;
import com.taxe.game.GameCore;
import com.taxe.game.InputHandling.Clickable;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Tracks.Sleeper;

import java.util.ArrayDeque;
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
    private ArrayDeque<Sleeper> pathSleepers;
    private Node node;

    private int state;

    public Train(int speed, int cargoCap, int fuelCost, String id, Node node) {
        this.speed = speed;
        this.cargoCap = cargoCap;
        this.fuelCost = fuelCost;
        this.id = id;
        this.cargo = null;
        this.pathNodes = new ArrayDeque<>();
        this.pathSleepers = new ArrayDeque<>();
        this.node = node;
        this.setPosition(node.getX(), node.getY());
        this.setState(TrainStates.ACTIVE);
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

    public Deque<Sleeper> getPathSleepers() {
        return pathSleepers;
    }

    public void setPath(Deque<Node> nodes, Deque<Sleeper> sleepers) {
        this.pathNodes = new ArrayDeque<>(nodes);
        this.pathSleepers = new ArrayDeque<>(sleepers);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public abstract Texture getTexture();

    public abstract void adjustActor();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        adjustActor();
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

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        return x >= -getOriginX() && x < getWidth() - getOriginX() && y >= -getOriginY() && y < getHeight() - getOriginY() ? this : null;
    }

    public void clicked(GameCore game) {
        if (getState() == TrainStates.ACTIVE) {
            StartPathCommand.executeCommand(game, this);
        } else if (getState() == TrainStates.SELECTED) {
            ResetPathCommand.executeCommand(game, this);
        }
    }

}
