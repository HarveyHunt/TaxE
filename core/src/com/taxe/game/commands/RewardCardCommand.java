package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.player.Player;

import java.util.Random;

/**
 * Created by henry on 09/02/15.
 * Reward the player specified in the target argument with a randomly selected card
 */
public class RewardCardCommand implements Commandable {
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
