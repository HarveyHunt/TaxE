package com.taxe.game;

import com.taxe.game.trains.Train;
import com.taxe.game.trains.TrainType;

/**
 * Created by Vladimir on 19/11/2014.
 */
public class Homebase {

    final private Coordinate position;
    final private int maxHealth;
    private int health;
    private TrainType currentlyBuiltTrain;

    public Homebase(Coordinate position, int health) {
        this.position = position;
        this.maxHealth = health;
        this.health = health;
        this.currentlyBuiltTrain = null;
    }

    public Coordinate getPosition() {
        return position;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void buildTrain(TrainType trainType) {
        // How does this happen?
        // Interacting with turns?
        // Maybe create method updateBuildingProcess() ?
        currentlyBuiltTrain = trainType;
    }
}
