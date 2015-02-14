package com.taxe.game.tasks;

import com.taxe.game.GameCore;
import com.taxe.game.cargo.Bears;
import com.taxe.game.cargo.Coal;
import com.taxe.game.cargo.Penguins;
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
        City newEnd;
        int turns = rgen.nextInt(5) + 1;
        int cargoQuantity = rgen.nextInt(100);

        do
            newEnd = cities.get(rgen.nextInt(cities.size() - 1));
        while (!unusedCity(newEnd));

        switch(rgen.nextInt(4)) {
            case 0:
                return new Task(newEnd, new Wheat(cargoQuantity), turns);
            case 1:
                return new Task(newEnd, new Coal(cargoQuantity), turns);
            case 2:
                return new Task(newEnd, new Penguins(cargoQuantity), turns);
            case 3:
                return new Task(newEnd, new Bears(cargoQuantity), turns);
        }

        // We'll never get here, but Java doesn't believe that....
        return null;
    }

    /**
     * Discover if a city is already the end point of a task, if so return false.
     * @param c The city to be checked for.
     * @return true if the city is unused, false otherwise.
     */
    private boolean unusedCity(City c) {
        for (Task t : game.getTasks())
            if (t.getEndCity() == c)
                return false;
        return true;
    }
}
