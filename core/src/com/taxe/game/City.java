package com.taxe.game;

import java.util.ArrayList;

/**
 * Created by Owen on 19/11/2014.
 */
public class City {

    private Node node;
    private ArrayList<Task> tasks;
    private Influence influence;
    private ArrayList<CargoType> cargoTypes;

    public City(Node node, ArrayList<Task> tasks, Influence influence, ArrayList<CargoType> cargoTypes) {
        this.node = node;
        this.cargoTypes = cargoTypes;
        this.influence = influence;
        this.tasks = tasks;
    }

    public Node getNode() {
        return node;
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

    public void draw() {
        // Draw the city on the screen
    }

}
