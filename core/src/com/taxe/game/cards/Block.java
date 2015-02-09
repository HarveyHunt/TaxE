package com.taxe.game.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.nodes.NodeStates;
import com.taxe.game.util.Coordinate;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.commands.Commands;

import java.util.Random;

/**
 * Created by henry on 09/02/15.
 */
public class Block extends Card {
    public Block() {
        command = Commands.blockCityCommand;
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
