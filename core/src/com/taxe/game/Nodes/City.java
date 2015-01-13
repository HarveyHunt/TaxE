package com.taxe.game.Nodes;

import com.taxe.game.Cargo.Cargo;
import com.taxe.game.Util.Coordinate;
import com.taxe.game.Player;
import com.taxe.game.Resources.Influence;
import com.taxe.game.Tasks.Task;
import com.taxe.game.Util.Textures;

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
        super(Textures.CITY);
        influence = null;
        taskList = new ArrayList<>();
        cargoList = new ArrayList<>();
    }

    public City(Coordinate coordinate, String id) {
        super(coordinate, id, Textures.CITY);
        influence = null;
        taskList = new ArrayList<>();
        cargoList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public Influence getInfluence() {
        return influence;
    }

    public double getInfluence(Player p) {
        return influence.getInfluence(p);
    }

    public void changeInfluenceBy(Player player, double delta) {
        influence.changeInfluenceBy(player, delta);
    }

}
