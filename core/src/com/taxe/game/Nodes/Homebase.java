package com.taxe.game.Nodes;

import com.taxe.game.Textures;
import com.taxe.game.Trains.Train;

/**
 * Homebase is a place where player starts the game and builds trains.
 * In order to win the game, player must destroy opponent's homebase.
 */
public class Homebase extends Node {

    private final int maxHealth;
    private int health;
    private Train currentBuild;
    private int turnsTillBuilt;

    public Homebase() {
        super(Textures.HOMEBASE);
        maxHealth = 0;
        health = 0;
        currentBuild = null;
        turnsTillBuilt = 0;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void changeHealthBy(int delta) {
        health += delta;
    }

    public Train getCurrentBuild() {
        return currentBuild;
    }

    public void startBuild(Train build, int turns) {
        currentBuild = build;
        turnsTillBuilt = turns;
    }

    public void updateBuild() {
        turnsTillBuilt = Integer.max(0, turnsTillBuilt - 1);
    }

    public boolean buildFinished() {
        return turnsTillBuilt == 0;
    }

}
