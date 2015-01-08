package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Vlad on 19/11/2014.
 * Class representing players' homebases in the game.
 */
public class Homebase extends Node {

    final private int maxHealth;
    private int health;
    private TrainType currentBuild;

    public Homebase() {
        super(new Texture("homebase.png"));
        maxHealth = 0;
        health = 0;
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

    public TrainType getCurrentBuild() {
        return currentBuild;
    }

    public void startBuild(TrainType build) {
        currentBuild = build;
    }

    public void updateBuild() {
        // Not sure how this will interact with game loop and turns
    }

}
