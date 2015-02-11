package com.taxe.game.nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * City is a node that issues tasks and trades cargo. Completing tasks set by cities increases player's influence and
 * gives rewards.
 *
 * NOTE: influence represents player one's influence.
 *
 * @see com.taxe.game.tasks.Task
 * @see com.taxe.game.cargo.Cargo
 */
public class City extends Node {

    private float influence;
    private ArrayList<Task> taskList;
    private ArrayList<Cargo> cargoList;
    public Boolean locked;

    /**
     * Default constructor. Necessary for {@link #readNodes(String)}
     */
    public City() {
        super();
        influence = 0.5f;
        locked = false;
        taskList = new ArrayList<>();
        cargoList = new ArrayList<>();
    }

    public void adjustActor() {
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 5f);
        setTouchable(Touchable.enabled);
    }

    /* TODO: Change texture if we are locked. */
    public Texture getTexture() {
        return NodeTextures.CITY[getState()];
    }

    /**
     * Returns list of tasks city is holding.
     *
     * @return list of city's tasks.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns list of cargo city is selling.
     *
     * @return list of city's cargo.
     */
    public List<Cargo> getCargoList() {
        return cargoList;
    }

    /**
     * Returns influence a player has in the city
     *
     * The variable influence represents Player one's influence in a city,
     * Player two's influence is simply 1 - influence.
     *
     * @param playerID represents if the player is player one or two.
     * @return influence of player p in the city
     */
    public float getInfluence(int playerID) {
        return playerID == 0 ? influence : 1.0f - influence;
    }

    /**
     * Changes influence of a player in the city.
     *
     * The variable influence represents Player one's influence in a city,
     * Player two's influence is simply 1 - influence.
     *
     * @param playerID represents if the player is player one or two.
     * @param delta  change of influence
     */
    public void changeInfluenceBy(int playerID, float delta) {
        influence = Math.max(0, playerID == 0 ? influence + delta
                : (1.0f - influence) + delta);
    }
}
