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
    private Texture texture = new Texture("city.png");

    public Homebase() {super(); maxHealth = 0; health = 0; currentBuild = null;}

    public Homebase(Coordinate coordinate, boolean passable, int maxHealth) {
        super(coordinate, passable);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.currentBuild = null;
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

    public void draw(SpriteBatch batch) {
        Coordinate c = getCoordinate();
        batch.draw(texture, (float)c.getX(), (float)c.getY(), 150, 150);
    }
}
