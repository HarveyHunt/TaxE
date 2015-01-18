package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.Player;

/**
 * Created by Owen on 16/01/2015.
 */
public class DealDamageCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("target must be an instance of Node");
        }
        ((Player) target).getHomebase().changeHealthBy(-100);
        if (((Player) target).getHomebase().getHealth() == 0) {
            Commands.endGameCommand.executeCommand(game, target);
        }
        // Update the health values displayed in the HUD
        game.getGui().getHUD().setHealth(
                game.getMap().getHomebases().get(0).getHealth(),  // player 1
                game.getMap().getHomebases().get(1).getHealth()); // player 2
    }

}
