package com.taxe.game;

/**
 * Created by Vlad on 19/11/2014.
 * Class representing players' homebases in the game.
 */
public class Homebase {

    final private Node node;
    final private int maxHealth;
    private int health;
    private TrainType currentBuild;

    public Homebase(Node node, int health) {
        this.node = node;
        this.maxHealth = health;
        this.health = health;
        this.currentBuild= null;
    }

    public Node getNode() {
        return node;
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
