package com.taxe.game.nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.player.Player;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.resources.Influence;
import com.taxe.game.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * City is a node that issues tasks and trades cargo. Completing tasks set by cities increases player's influence and
 * gives rewards.
 *
 * @see com.taxe.game.resources.Influence
 * @see com.taxe.game.tasks.Task
 * @see com.taxe.game.cargo.Cargo
 */
public class City extends Node {

    private Influence influence;
    private ArrayList<Task> taskList;
    private ArrayList<Cargo> cargoList;
    public Boolean locked;

    /**
     * Default constructor. Necessary for {@link #readNodes(String)}
     */
    public City() {
        super();
        influence = null;
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
     * Initialise influence outside of the constructor.
     *
     * It isn't very neat to pass players into City's constructor - this
     * is nicer.
     * @param players An arraylist of all players in the game.
     */
    public void initInfluence(ArrayList<Player> players) {
        influence = new Influence(players);
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
     * @param p player
     * @return influence of player p in the city
     */
    public double getInfluence(Player p) {
        return influence.getInfluence(p);
    }

    /**
     * Changes influence of a player in the city. Influences of other players are adjusted accordingly.
     *
     * @param player player
     * @param delta  change of influence
     */
    public void changeInfluenceBy(Player player, float delta) {
        influence.changeInfluenceBy(player, delta);
    }

}
