package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Player;
import com.taxe.game.Util.Textures;
import com.taxe.game.Trains.Train;

/**
 * Created by vlad on 12/01/15.
 */
public class ActivatePlayerCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("target must be an instance of Player");
        }
        for (Player p: game.getPlayers()) {
            for (Train t: p.getTrains()) {
                t.setState(Textures.ORIGINAL);
            }
        }
        for (Train t: ((Player) target).getTrains()) {
            t.setState(Textures.HIGHLIGHTED);
        }
    }

}
