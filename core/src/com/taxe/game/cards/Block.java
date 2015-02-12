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

        command = Commands.blockCityCommand;
        setOrigin(100, 100);
        setSize(getTexture().getWidth(), getTexture().getHeight());
    }

    public Texture getTexture() {
        return CardTextures.BLOCK_CARD;
    }

    @Override
    public void clicked(GameCore game) {
        command.executeCommand(game, null);
    }
}
