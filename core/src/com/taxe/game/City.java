package com.taxe.game;

import java.util.ArrayList;

/**
 * Created by Owen on 19/11/2014.
 */
public class City {

    private Coordinate position;
    private ArrayList <Task> tasks;
    private Influence influence;
    private ArrayList<Cargo> cargo;

    public City(Coordinate position, ArrayList<Cargo> cargo){
        this.position = position;
        this.cargo = cargo;
        this.influence = new Influence();
        this.tasks = new ArrayList<Task>();
    }

    public void createTask(){
        // Add a new task
    }

    public void removeTask(){
        // Remove a task
    }

    public Coordinate getPosition(){
        return position;
    }

    public ArrayList<Task> getTaskList(){
        return tasks;
    }

    public ArrayList<Cargo> getCargo(){
        return cargo;
    }

    public void draw(){
        // Draw the city on da screen
    }

}
