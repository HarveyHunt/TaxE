package com.taxe.game.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.nodes.NodeStates;
import com.taxe.game.util.Coordinate;
import com.badlogic.gdx.graphics.Texture;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.commands.Commands;

import java.util.Random;

/**
 * Created by henry on 09/02/15.
 */
public class Block extends Card {
    public Block() {
        super();
        command = Commands.blockCityCommand;

        qtyLabel.setPosition(112, 42);

        setPosition(64, 0);
        setSize(getTexture().getWidth(), getTexture().getHeight());
    }

    public Texture getTexture() {
        return CardTextures.BLOCK_CARD;
    }

    @Override
    public void clicked(GameCore game) {
        if (game.getActivePlayer().getBlockQty() > 0) {
            command.executeCommand(game, null);
            game.getActivePlayer().adjustBlockQty(-1);

            game.hand.UpdateCardLabels(game);
        }

        else {
            System.out.println("Insufficient Block Cards!");
        }
    }
}
