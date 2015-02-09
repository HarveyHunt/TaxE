package com.taxe.game.tasks;

import com.taxe.game.GameCore;
import com.taxe.game.cargo.Coal;
import com.taxe.game.cargo.Wheat;
import com.taxe.game.nodes.City;

import java.util.List;
import java.util.Random;

public class TaskFactory {

    public GameCore game;

    public TaskFactory(GameCore game) {
        this.game = game;
    }

    public Task generateTask() {
        List<City> cities = this.game.getMap().getCities();
        Random rgen = new Random();
        City newEnd = cities.get(rgen.nextInt(cities.size() - 1));

        if (rgen.nextInt(2) == 0)
            return new Task(newEnd, new Wheat((rgen.nextInt(100))), rgen.nextInt(5) + 1);
        else
            return new Task(newEnd, new Coal(rgen.nextInt(100)), rgen.nextInt(5) + 1);
    }
}
