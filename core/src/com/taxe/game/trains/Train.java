package com.taxe.game.trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.GameCore;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.commands.Commands;
import com.taxe.game.inputhandling.Clickable;
import com.taxe.game.nodes.Node;
import com.taxe.game.tracks.Sleeper;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Base-class for representing trains in the game. Different types of trains should be implemented by extending this
 * class. Each train has a certain speed, fuel cost, and cargo capacity and each train type has a unique id. Depending
 * on the state of a train, train has different texture and player has different interactions available for it. States
 * of all train are stored in {@link com.taxe.game.trains.TrainStates} and textures are stored in {@link
 * com.taxe.game.trains.TrainTextures}.
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

    /**
     * Creates a new train at the specified node, with given speed,
     * cargo capacity and fuel cost.
     *
     * @param speed    train speed, must be non-negative
     * @param cargoCap cargo capacity, must be non-negative
     * @param fuelCost fuel cost, must be non-negative
     * @param node     starting node, must be not equal to null
     */
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
        this.setState(TrainStates.INACTIVE);
        validate();
    }

    /**
     * Returns speed of a train.
     *
     * @return speed of a train.
     */
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    /**
     * Returns fuel cost of a train.
     *
     * @return fuel cost of a train.
     */
    public int getFuelCost() {
        return fuelCost;
    }

    /**
     * Returns id representing train type.
     *
     * @return id of a train.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns cargo capacity of a train.
     *
     * @return cargo capacity of a train.
     */
    public int getCargoCap() {
        return cargoCap;
    }

    /**
     * Returns cargo the train is currently holding.
     *
     * @return cargo the train is holding.
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * Loads the cargo onto the train
     *
     * @param cargo cargo to be loaded onto the train
     */
    public boolean loadCargo(Cargo cargo) {
        if (cargo.getQuantity() > cargoCap || this.cargo != null)
            return false;

        this.cargo = cargo;
        return true;
    }

    /**
     * Remove the cargo from the train.
     */
    public void unloadCargo() {
        cargo = null;
    }

    /**
     * Returns a deque-view of nodes on the current train path. Modifying the returned deque will result in modifying
     * trains path.
     *
     * @return deque of nodes of the current train path.
     */
    public Deque<Node> getPathNodes() {
        return pathNodes;
    }

    /**
     * Returns a deque-view of sleepers on the current train path. Modifying the returned deque will result in modifying
     * trains path.
     *
     * @return deque of sleepers of the current train path.
     */
    public Deque<Sleeper> getPathSleepers() {
        return pathSleepers;
    }

    /**
     * Sets the train movement path. List of sleepers must correspond to the path going through specified nodes.
     *
     * @param nodes    nodes on the path
     * @param sleepers sleepers on the path
     */
    public void setPath(Deque<Node> nodes, Deque<Sleeper> sleepers) {
        this.pathNodes = new ArrayDeque<>(nodes);
        this.pathSleepers = new ArrayDeque<>(sleepers);
    }

    /**
     * Returns node where the train is located now
     *
     * @return node where train is now
     */
    public Node getNode() {
        return node;
    }

    /**
     * Sets the current node of the train to the given
     *
     * @param node new location of the train
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * Returns texture representing train type at the current state.
     *
     * @return texture of train.
     */
    public abstract Texture getTexture();

    /**
     * Returns current state of a train.
     *
     * @return state of a train.
     */
    public int getState() {
        return state;
    }

    /**
     * Updates the state of a train.
     *
     * @param state new state
     */
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

        if (cargo != null)
            // Height is divided by 3 as a cargo's texture is three images
            // stacked on top of each other.
            batch.draw(cargo.getTexture(),
                    getX() - getOriginX(), getY() - getOriginY(),
                    getOriginX(), getOriginY(),
                    cargo.getTexture().getWidth(), cargo.getTexture().getHeight() / 3,
                    getScaleX(), getScaleY(),
                    getRotation(),
                    0, 0, cargo.getTexture().getWidth(), cargo.getTexture().getHeight() / 3,
                    false, false);
    }

    public void adjustActor() {
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setTouchable(Touchable.enabled);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && this.getTouchable() != Touchable.enabled) return null;
        return x >= -getOriginX() && x < getWidth() - getOriginX() && y >= -getOriginY() && y < getHeight() - getOriginY() ? this : null;
    }

    public void clicked(GameCore game) {
        if (getState() == TrainStates.ACTIVE) {
            Commands.startPathCommand.executeCommand(game, this);
        } else if (getState() == TrainStates.SELECTED) {
            Commands.resetPathCommand.executeCommand(game, null);
        }
    }

    /**
     * Validates state of node's variable. Some subclasses of Train do require overriding this method.
     *
     * @throws RuntimeException if speed is negative, or cargoCap is negative, or fuelCost is negative, or node is null
     */
    protected void validate() throws RuntimeException {
        if (speed < 0) throw new RuntimeException("speed < 0");
        if (cargoCap < 0) throw new RuntimeException("cargoCap < 0");
        if (fuelCost < 0) throw new RuntimeException("fuelCost < 0");
        if (node == null) throw new RuntimeException("node == null");
    }

}