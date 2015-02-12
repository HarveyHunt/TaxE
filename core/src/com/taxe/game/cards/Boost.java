package com.taxe.game.cards;

import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;
import com.taxe.game.nodes.City;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

/**
 * Created by henry on 09/02/15.
 */
public class Boost extends Card {
    public Boost() {
        command = Commands.boostTrainCommand;
    }

    public Texture getTexture() {
        // Temp
        return CardTextures.BLOCK_CARD;
    }


    @Override
    public void clicked(GameCore game) {
        command.executeCommand(game, null);
    }
}
