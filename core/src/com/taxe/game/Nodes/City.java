package com.taxe.game.Nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.Cargo.Cargo;
import com.taxe.game.Player;
import com.taxe.game.Resources.Influence;
import com.taxe.game.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * City is a place that issues tasks and trades cargo.
 * Completing tasks set by cities increases player's influence and give rewards.
 */
public class City extends Node {

    private Influence influence;
    private ArrayList<Task> taskList;
    private ArrayList<Cargo> cargoList;

    public City() {
        super();
        influence = null;
        taskList = new ArrayList<>();
        cargoList = new ArrayList<>();
    }

    public void adjustActor() {
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 5f);
        setTouchable(Touchable.enabled);
    }

    public Texture getTexture() {
        return NodeTextures.CITY[getState()];
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public double getInfluence(Player p) {
        return influence.getInfluence(p);
    }

    public void changeInfluenceBy(Player player, double delta) {
        influence.changeInfluenceBy(player, delta);
    }

}
