package com.taxe.game.nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.trains.Train;

/**
 * Homebase is a node where player starts the game and builds trains.
 * Homebase is created with some maximum amount of health. When health reaches 0, homebase is considered to be destroyed.
 * @see com.taxe.game.trains.Train
 */
public class Homebase extends Node {

    private final int maxHealth;
    private int health;
    private Train currentBuild;
    private int turnsTillBuilt;

    /**
     * Default constructor. Necessary for {@link #readNodes(String)}.
     */
    public Homebase() {
        super();
        maxHealth = 0;
        health = 0;
        currentBuild = null;
        turnsTillBuilt = 0;
        validateHealth();
    }

    public Texture getTexture() {
        return NodeTextures.HOMEBASE[getState()];
    }

    public void adjustActor() {
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 5f);
        setTouchable(Touchable.enabled);
    }

    /**
     * Returns maximum amount of health.
     * @return maximum amount of health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Returns current amount of health.
     * @return current amount of health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Changes health of homebase by delta.
     * @param delta by how much health of homebase is changed.
     */
    public void changeHealthBy(int delta) {
        health = Integer.max(0, Integer.min(maxHealth, health + delta));
        validateHealth();
    }

    /**
     * Returns the train homebase is currently building.
     * @return train homebase is building.
     */
    public Train getCurrentBuild() {
        return currentBuild;
    }

    /**
     * Starts building a train.
     * @param build train to be built.
     * @param turns number of turns building a train will take.
     */
    public void startBuild(Train build, int turns) {
        currentBuild = build;
        turnsTillBuilt = turns;
    }

    /**
     * Advances building train by one turn.
     */
    public void updateBuild() {
        turnsTillBuilt = Integer.max(0, turnsTillBuilt - 1);
    }

    /**
     * Returns true if all trains have finished building.
     * @return true if no trains are currently being built, false otherwise.
     */
    public boolean buildFinished() {
        return turnsTillBuilt == 0;
    }

    /**
     * Checks if health and maxHealth follow the constraints.
     * @throws AssertionError if health < 0, or maxHealth < 0, or health > maxHealth.
     */
    private void validateHealth() throws AssertionError {
        assert health >= 0;
        assert health <= maxHealth;
        assert maxHealth >= 0;
    }

}
