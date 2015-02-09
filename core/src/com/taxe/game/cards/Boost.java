package com.taxe.game.cards;

import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;
import com.taxe.game.nodes.City;

import java.util.Random;

/**
 * Created by henry on 09/02/15.
 */
public class Boost extends Card {
    public Boost() {
        command = Commands.boostTrainCommand;
    }

    @Override
    public void clicked(GameCore game) {
        // On activation, the player selects a city, and the lock command is called on it
        City target = null;
        Random rand = new Random();

        // City selection phase
        // -- TEMP -- The city will always be selected at random for proof-of-concept
        // Ideally this would be the user interacting with the GUI
        target = game.getMap().getCities().get(rand.nextInt(game.getMap().getCities().size()));

        // Validation stage (ensure that the city is not already locked, and unoccupied?)
        // -- TEMP -- Ignored for the time being

        // Execution stage
        Commands.lockCityCommand.executeCommand(game, target);
    }
}
