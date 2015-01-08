package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Owen on 19/11/2014.
 */
public class City extends Node {

    private ArrayList<Task> tasks;
    private Influence influence;
    private ArrayList<CargoType> cargoTypes;

    public City() {
        super(new Texture("city.png"));
        tasks = null;
        influence = null;
        cargoTypes = null;
    }

    public Coordinate getCoordinate() {
        return super.getCoordinate();
    }

    public void addCargoType(CargoType cargoType) {
        cargoTypes.add(cargoType);
    }

    public void removeCargoType(CargoType cargoType) {
        if (cargoTypes.contains(cargoType)) {
            cargoTypes.remove(cargoType);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        if (tasks.contains(task)) {
            tasks.remove(task);
        }
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public ArrayList<CargoType> getCargoTypes() {
        return cargoTypes;
    }

}
