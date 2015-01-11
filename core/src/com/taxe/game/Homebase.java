package com.taxe.game;

/**
 * Created by Vlad on 19/11/2014.
 * Class representing players' homebases in the game.
 */
public class Homebase extends Node {

    final private int maxHealth;
    private int health;
    private Train currentBuild;

    public Homebase() {
        super(Textures.HOMEBASE);
        maxHealth = 100;
        health = 100;
        currentBuild = null;
    }

    public String toString() {
        return "Homebase <" +
                super.toString() +  ", " +
                "maxHealth = " + Integer.toString(maxHealth) + ", " +
                "health = " + Integer.toString(health) + ">";
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

    public void startBuild(Train build) {
        currentBuild = build;
    }

    public void updateBuild() {
        // Not sure how this will interact with game loop and turns
    }

}
