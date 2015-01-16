package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Player;
import com.taxe.game.Trains.Train;

/**
 * Created by Owen on 16/01/2015.
 */
public class EndMovementCommand implements Commandable {

    public static boolean executeCommand(GameCore game, Object target) {
        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("target must be an instance of Node");
        }
        for (Train t: ((Player) target).getTrains()) {
            if (t.getActions().size != 0) {
                return false;
            }
        }
        game.getGui().getHUD().unlockButtons();
        return true;
    }

}
