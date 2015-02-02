package com.taxe.game.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.Main;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.Homebase;
import com.taxe.game.resources.Fuel;
import com.taxe.game.resources.Gold;
import com.taxe.game.tasks.Task;
import com.taxe.game.trains.Train;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents players in the game. Players have their homebase, trains, gold and fuel. Players build trains at their
 * homebase. Building train costs fuel and gold.
 * @see com.taxe.game.trains
 * @see com.taxe.game.resources.Gold
 * @see com.taxe.game.resources.Fuel
 * @see com.taxe.game.nodes.Homebase
 */
public class Player extends Group {

    private final ArrayList<Train> trains;
    public ArrayList<Task> tasks;
    private final Gold gold;
    private final Fuel fuel;
    private final Homebase homebase;

    /**
     * Creates a player with a specified homebase, set of trains, gold and fuel resources
     *
     * @param tasks     list of tasks
     * @param homebase  homebase
     * @param trains    list of trains
     * @param gold      gold
     * @param fuel      fuel
     */
    public Player(Homebase homebase, ArrayList<Train> trains, ArrayList<Task> tasks, Gold gold, Fuel fuel) {
        this.homebase = homebase;
        this.trains = new ArrayList<>(trains);
        this.tasks = new ArrayList<>(tasks);
        this.gold = gold;
        this.fuel = fuel;
        for (Train t : trains) {
            this.addTrain(t);
            this.addActor(t);
        }
    }

    /**
     * Sets all of the trains to be touchable or not
     *
     * @param touchable if the trains are touchable or not. Touchable.enabled or Touchable.disabled
     */
    public void setTrainsTouchable(Touchable touchable) {
        for (Train t : trains) {
            t.setTouchable(touchable);
        }
    }

    /**
     * Returns player's trains
     *
     * @return a list of player's trains
     */
    public List<Train> getTrains() {
        return trains;
    }

    /**
     * Returns player's gold
     *
     * @return player's gold
     */
    public Gold getGold() {
        return gold;
    }

    /**
     * Returns player's homebase
     *
     * @return player's homebase
     */
    public Homebase getHomebase() {
        return homebase;
    }

    /**
     * Returns player's fuel
     *
     * @return player's fuel
     */
    public Fuel getFuel() {
        return fuel;
    }

    /**
     * Adds train to player's possessions. Adding a train will subtract train.getFuelCost() from player's fuel
     * resource. This method may be redefined later to allow building trains.
     *
     * @param t train to be added
     */
    public void addTrain(Train t) {
        trains.add(t);
        this.addActor(t);
        fuel.changeUsedFuelBy(t.getFuelCost());
    }

    /**
     * Function to be called when the player completes a goal.
     */
    public void completeTask(Task task, City city) {
        city.changeInfluenceBy(this, 0.1f);
        this.tasks.remove(task);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawChildren(batch, parentAlpha);
    }


}
