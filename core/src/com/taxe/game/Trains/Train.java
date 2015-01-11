package com.taxe.game.Trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.Cargo.Cargo;
import com.taxe.game.Coordinate;
import com.taxe.game.Map;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Player;
import com.taxe.game.Textures;

import java.util.ArrayDeque;

/**
 * Created by vlad on 10/01/15.
 */
public abstract class Train extends Actor {

    private int speed;
    private int cargoCap;
    private int fuelCost;
    private final String id;

    private Cargo cargo;
    private ArrayDeque<Node> path;
    private Node node;
    private Coordinate coordinate;

    private Player owner;
    private Texture[] textures;
    private int currentTexture;

    public Train(int speed, int cargoCap, int fuelCost, String id, Node node, Player owner, Texture[] textures) {
        this.speed = speed;
        this.cargoCap = cargoCap;
        this.fuelCost = fuelCost;
        this.id = id;
        this.cargo = null;
        this.path = null;
        this.node = node;
        this.coordinate = node.getCoordinate();
        this.owner = owner;
        this.textures = textures;
        this.currentTexture = Textures.ORIGINAL;
        setBounds((float) coordinate.getX() - textures[currentTexture].getWidth() / 2, (float) coordinate.getY() - textures[currentTexture].getHeight() / 2,
                textures[currentTexture].getWidth(), textures[currentTexture].getHeight());
        setTouchable(Touchable.enabled);
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
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Player getOwner() {
        return owner;
    }

    public int getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(int texture) {
        currentTexture = texture;
    }

    public boolean isActive() {
        return currentTexture == Textures.HIGHLIGHTED;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = (float) coordinate.getX();
        float y = (float) coordinate.getY();
        batch.draw(textures[currentTexture], x - textures[currentTexture].getWidth() / 2, y - textures[currentTexture].getHeight() / 2);
    }

    public void processTrainClick(Map m) {
        if (getCurrentTexture() == Textures.SELECTED) {
            setCurrentTexture(Textures.ORIGINAL);
            m.changeAllTextures(Textures.SELECTED, Textures.ORIGINAL);
            m.changeAllTextures(Textures.HIGHLIGHTED, Textures.ORIGINAL);
            m.clearTrainPath();
        } else if (getCurrentTexture() == Textures.ORIGINAL) {
            setCurrentTexture(Textures.SELECTED);
            Node n = getNode();
            n.setState(Textures.SELECTED);
            m.changeNeighbourTexture(n, Textures.ORIGINAL, Textures.HIGHLIGHTED);
            m.initTrainPath(n);
        }
    }


}
