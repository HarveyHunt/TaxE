package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Created by henry on 09/02/15.
 */
public class BlockCityCommand implements Commandable {
    @Override
    public void executeCommand(GameCore game, Object target) {
        Commands.resetPathCommand.executeCommand(game, target);
        game.hand.setBlockCardState(true);
        game.hand.setCardUsability(false);
    }
}
