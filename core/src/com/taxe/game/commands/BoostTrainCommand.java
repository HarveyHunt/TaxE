package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.trains.Train;

/**
 * Increase the speed of a train.
 */
public class BoostTrainCommand implements Commandable {
    /**
     * @param game   An instance of GameCore.
     * @param target Unused.
     */
    public void executeCommand(GameCore game, Object target) {
        for (Train t : game.getActivePlayer().getTrains())
            t.setSpeed(t.getSpeed() + 2);

        game.hand.setBoostCardState(true);
        game.hand.setCardUsability(false);
    }
}
