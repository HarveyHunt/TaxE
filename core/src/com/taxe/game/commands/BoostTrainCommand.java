package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.trains.Train;

public class BoostTrainCommand implements Commandable {
    @Override
    public void executeCommand(GameCore game, Object target) {
        for (Train t : game.getActivePlayer().getTrains())
            t.setSpeed(t.getSpeed() + 2);

        game.hand.setBoostCardState(true);
        game.hand.setCardUsability(false);
    }
}
