package com.taxe.game.Trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.Cargo.Cargo;
import com.taxe.game.Commands.ResetPathCommand;
import com.taxe.game.Commands.StartPathCommand;
import com.taxe.game.Coordinate;
import com.taxe.game.GameCore;
import com.taxe.game.InputHandling.Clickable;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Player;
import com.taxe.game.Textures;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vlad on 10/01/15.
 */
public abstract class Train extends Actor implements Clickable {

    private final String id;
    private int speed;
    private int cargoCap;
    private int fuelCost;
    private Cargo cargo;
    private ArrayDeque<Node> path;
    private Node node;
    private Coordinate coordinate;

    private ArrayList<Texture> textures;
    private int state;

    public Train(int speed, int cargoCap, int fuelCost, String id, Node node, Player owner, Texture[] textures) {
        this.speed = speed;
        this.cargoCap = cargoCap;
        this.fuelCost = fuelCost;
        this.id = id;
        this.cargo = null;
        this.path = null;
        this.node = node;
        this.coordinate = node.getCoordinate();
        this.textures = new ArrayList<>(Arrays.asList(textures));
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

    public ArrayDeque<Node> getPath() {
        return path;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
        this.coordinate = node.getCoordinate();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Texture getTexture() {
        return textures.get(state);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        float x = (float) coordinate.getX();
        float y = (float) coordinate.getY();
        Texture t = getTexture();
        setBounds(x - t.getWidth() / 2, y - t.getHeight() / 2, t.getWidth(), t.getHeight());
        setTouchable((state == Textures.ORIGINAL) ? Touchable.disabled : Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = (float) coordinate.getX();
        float y = (float) coordinate.getY();
        Texture t = getTexture();
        batch.draw(t, x - t.getWidth() / 2, y - t.getHeight() / 2);
    }

    public void clicked(GameCore game) {
        if (getState() == Textures.HIGHLIGHTED) {
            new StartPathCommand().executeCommand(game, this);
        } else if (getState() == Textures.SELECTED) {
            new ResetPathCommand().executeCommand(game, this);
        }
    }

}
