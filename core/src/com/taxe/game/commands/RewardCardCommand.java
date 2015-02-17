package com.taxe.game.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.player.Player;
import com.taxe.game.tasks.Task;
import com.taxe.game.trains.Train;
import com.taxe.game.trains.TrainStates;
import com.taxe.game.util.Coordinate;

import java.util.Random;

/**
 * Created by henry on 09/02/15.
 * Reward the player specified in the target argument with a randomly selected card
 */
public class RewardCardCommand implements Commandable{
    @Override
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {

        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("Target not instance of Player");
        }

        Random rand = new Random();
        Player player = (Player) target;

        if (rand.nextInt(2) == 1) {
            player.adjustBlockQty(1);
        } else {
            player.adjustBoostQty(1);
        }

        game.hand.updateCardLabels(game);
    }
}
