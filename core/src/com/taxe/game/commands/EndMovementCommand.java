package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.player.Player;
import com.taxe.game.trains.Train;

/**
 * Created by Owen on 16/01/2015.
 */
public class EndMovementCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("target must be an instance of Player");
        }
        for (Train t : ((Player) target).getTrains()) {
            if (t.getActions().size != 0) {
                break;
            }
        }
        game.getGui().getHUD().unlockButtons();
        Commands.switchPlayerCommand.executeCommand(game, null);
        Commands.dealDamageCommand.executeCommand(game, game.getActivePlayer());
    }

}
