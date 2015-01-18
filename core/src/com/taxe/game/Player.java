package com.taxe.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.nodes.Homebase;
import com.taxe.game.resources.Fuel;
import com.taxe.game.resources.Gold;
import com.taxe.game.trains.Train;

import java.util.ArrayList;

/**
 * Created by Vlad on 19/11/2014.
 */
public class Player extends Group {

    private final ArrayList<Train> trains;
    private final Gold gold;
    private final Fuel fuel;
    private final Homebase homebase;
    private int id; // 0 or 1

    public Player(Homebase homebase, int id, ArrayList<Train> trains, Gold gold, Fuel fuel) {
        this.homebase = homebase;
        this.id = id;
        this.trains = trains;
        this.gold = gold;
        this.fuel = fuel;
        if (this.trains != null) {
            for (Train t : trains) {
                this.addActor(t);
            }
        }
    }

    public int getId() {
        return id;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public Gold getGold() {
        return gold;
    }

    public Homebase getHomebase() {
        return homebase;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void addTrain(Train t) {
        trains.add(t);
        fuel.changeFuelCapBy(-t.getFuelCost());
        this.addActor(t);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawChildren(batch, parentAlpha);
    }


}
