package com.taxe.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

/**
 * Created by Vlad on 19/11/2014.
 */
public class Player extends Group {

    private final ArrayList<Train> trains;
    private final Gold gold;
    private final Fuel fuel;
    private final Homebase homebase;

    public Player(Homebase homebase, ArrayList<Train> trains, Gold gold, Fuel fuel) {
        this.homebase = homebase;
        this.trains = trains;
        this.gold = gold;
        this.fuel = fuel;
        for (Train t : trains) {
            this.addActor(t);
        }
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
        this.addActor(t);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawChildren(batch, parentAlpha);
    }


}
