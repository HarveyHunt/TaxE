package com.taxe.game.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.nodes.Homebase;
import com.taxe.game.trains.Train;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents players in the game. Players have their homebase, trains, gold,
 * fuelUsage and fuelCap. Players build trains at their
 * homebase. Building a train increases fuelUsage and gold.
 *
 * @see com.taxe.game.trains
 * @see com.taxe.game.nodes.Homebase
 */
public class Player extends Group {

    public final int id;
    private final ArrayList<Train> trains;
    private final Homebase homebase;
    private int gold;
    private int fuelUsage;
    private int fuelCap;

    private ArrayList<Integer> cardQtys;

    /**
     * Creates a player with a specified homebase, set of trains, gold and fuel resources
     *
     * @param homebase  homebase
     * @param trains    list of trains
     * @param gold      gold
     * @param fuelCap   fuelCap
     * @param fuelUsage fuelUsage
     * @param id        The id of this player
     */
    public Player(Homebase homebase, ArrayList<Train> trains,
                  int gold, int fuelUsage, int fuelCap, int id) {
        this.homebase = homebase;
        this.trains = new ArrayList<>(trains);
        this.gold = gold;
        this.fuelCap = fuelCap;
        this.fuelUsage = fuelUsage;
        for (Train t : trains) {
            this.addTrain(t);
            this.addActor(t);
        }

        this.id = id;

        /*
        List of integers representing the number of each card held
        Position 0: Boost
        Position 1: Block
        */

        this.cardQtys = new ArrayList<Integer>();

        cardQtys.add(0);
        cardQtys.add(0);
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
    public int getGold() {
        return gold;
    }

    /**
     * Change the amount of gold the player has
     *
     * @param delta How much to change the player's gold by
     */
    public void changeGold(int delta) {
        gold = Math.max(0, gold + delta);
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
     * Returns player's fuelUsage
     *
     * @return player's fuelUsage
     */
    public int getFuelUsage() {
        return fuelUsage;
    }

    /**
     * Returns player's fuelCap
     *
     * @return player's fuelCap
     */
    public int getFuelCap() {
        return fuelCap;
    }

    /**
     * Change the Player's fuelCap by delta.
     *
     * @param delta The amount to change the fuelCap by.
     */
    public void changeFuelCap(int delta) {
        fuelCap = Math.max(0, fuelCap + delta);
    }

    /**
     * Change the Player's fuelUsage by delta.
     *
     * @param delta The amount to change the fuelUsage by.
     */
    public void changeFuelUsage(int delta) {
        fuelUsage = Math.max(0, fuelUsage + delta);
    }

    public boolean fuelCapExceeded() {
        return fuelUsage > fuelCap;
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
        fuelUsage += t.getFuelCost();
    }

    public Integer getBoostQty() {
        return cardQtys.get(0);
    }

    public void adjustBoostQty(int v) {
        cardQtys.set(0, cardQtys.get(0) + v);
    }

    public Integer getBlockQty() {
        return cardQtys.get(1);
    }

    public void adjustBlockQty(int v) {
        cardQtys.set(1, cardQtys.get(1) + v);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawChildren(batch, parentAlpha);
    }
}