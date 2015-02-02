package com.taxe.game.tasks;

import com.taxe.game.GameCore;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.cargo.Coal;
import com.taxe.game.cargo.Penguins;
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

        Random random_generator = new Random();
        int random_index = random_generator.nextInt(cities.size()-1);

        City new_end = cities.get(random_index);
        int cargo_quantity = random_generator.nextInt(100);

        Cargo new_cargo;

        if (random_generator.nextInt(2) == 0) {
            new_cargo = new Penguins(cargo_quantity);
        } else {
            new_cargo = new Coal(cargo_quantity);
        }

        int new_time = random_generator.nextInt(5);

        return new Task(new_end, new_cargo, new_time);
    }
}
