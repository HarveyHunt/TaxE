package com.taxe.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.nodes.Homebase;
import com.taxe.game.resources.Fuel;
import com.taxe.game.resources.Gold;
import com.taxe.game.trains.Train;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents players in the game. Players have their homebase, trains, gold and fuel. Players build trains at their
 * homebase. Building train costs fuel and gold.
 */
public class Player extends Group {

    private final ArrayList<Train> trains;
    private final Gold gold;
    private final Fuel fuel;
    private final Homebase homebase;

    /**
     * Creates a player with a specified homebase, set of trains, gold and fuel resources
     *
     * @param homebase homebase
     * @param trains   list of trains
     * @param gold     gold
     * @param fuel     fuel
     */
    public Player(Homebase homebase, ArrayList<Train> trains, Gold gold, Fuel fuel) {
        this.homebase = homebase;
        this.trains = new ArrayList<>(trains);
        this.gold = gold;
        this.fuel = fuel;
        for (Train t : trains) {
            this.addTrain(t);
            this.addActor(t);
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
        fuel.changeFuelCapBy(-t.getFuelCost());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawChildren(batch, parentAlpha);
    }


}
