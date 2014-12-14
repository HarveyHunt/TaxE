package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Owen on 19/11/2014.
 */
public class City extends Node{

    private ArrayList<Task> tasks;
    private Influence influence;
    private ArrayList<CargoType> cargoTypes;
    private Texture texture = new Texture("city.png");

    public City(Coordinate coordinate, boolean passable, String id, Influence influence) {
        super(coordinate, passable);
        this.cargoTypes = new ArrayList<>();
        this.influence = influence;
        this.tasks = new ArrayList<>();
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

    public void draw(SpriteBatch batch) {
        batch.draw(texture, (float)getCoordinate().getX(), (float)getCoordinate().getY());
    }

}
