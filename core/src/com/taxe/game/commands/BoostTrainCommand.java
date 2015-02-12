package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Created by henry on 09/02/15.
 */
public class BoostTrainCommand implements Commandable {
    @Override
    public void executeCommand(GameCore game, Object o) {
        System.out.println("Boost Fired");
    }
}
