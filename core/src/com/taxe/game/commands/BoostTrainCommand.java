package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.trains.Train;

/**
 * Created by henry on 09/02/15.
 */
public class BoostTrainCommand implements Commandable {
    @Override
    public void executeCommand(GameCore game, Object target) {
        Train t = game.getActivePlayer().getTrains().get(0);
        t.setSpeed(t.getSpeed() + 2);

        game.hand.setBoostCardState(true);
        game.hand.setCardUsability(false);
    }
}
