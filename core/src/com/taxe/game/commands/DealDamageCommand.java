package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.gui.Healthbar;
import com.taxe.game.nodes.Homebase;
import com.taxe.game.player.Player;

/**
 * Created by Owen on 16/01/2015.
 */
public class DealDamageCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("target must be an instance of Node");
        }
        Player p = (Player) target;
        Homebase h = p.getHomebase();
        h.changeHealthBy(-100);
        game.getGui().getHUD().getHealthbar(p).setPercentage((float) h.getHealth() / h.getMaxHealth());

        if (h.getHealth() == 0) {
            Commands.endGameCommand.executeCommand(game, target);
        }
    }

}
