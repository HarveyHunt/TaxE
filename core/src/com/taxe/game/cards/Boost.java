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

        setX(0);
        setY(0);
        setSize(getTexture().getWidth(), getTexture().getHeight());
    }

    public Texture getTexture() {
        // Temp
        return CardTextures.BOOST_CARD;
    }


    @Override
    public void clicked(GameCore game) {
        command.executeCommand(game, null);
    }
}
