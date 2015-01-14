package com.taxe.game.Nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.Util.Coordinate;
import com.taxe.game.Util.Textures;
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
        validateHealth();
    }

    public Homebase(Coordinate coordinate, String id) {
        super(coordinate, id, Textures.HOMEBASE);
        maxHealth = 0;
        health = 0;
        currentBuild = null;
        turnsTillBuilt = 0;
        validateHealth();
    }

    public void setState(int state) {
        this.state = state;
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 5f);
        setBounds(getX() - getOriginX(), getY() - getOriginY(), t.getWidth(), t.getHeight());
        setPosition(getX() + getOriginX(), getY() + getOriginY());
        setTouchable(Touchable.enabled);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void changeHealthBy(int delta) {
        health = Integer.max(0, Integer.min(maxHealth, health + delta));
        validateHealth();
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

    private void validateHealth() throws RuntimeException {
        if (health < 0) {
            throw new RuntimeException("health must be non-negative");
        } else if (health > maxHealth) {
            throw new RuntimeException("health must not be greater than maxHealth");
        } else if (maxHealth < 0) {
            throw new RuntimeException("health must be non-negative");
        }
    }

}
