package com.taxe.game;

import java.util.ArrayList;

/**
 * Created by Vlad on 19/11/2014.
 */
public class Player {

    private final ArrayList<Train> trains;
    private final Gold gold;
    private final Fuel fuel;
    private final Homebase homebase;

    public Player(Homebase homebase, ArrayList<Train> trains, Gold gold, Fuel fuel) {
        this.homebase = homebase;
        this.trains = trains;
        this.gold = gold;
        this.fuel = fuel;
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

}
