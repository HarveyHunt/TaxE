package com.taxe.game.commands;

import com.taxe.game.GameCore;

public class BlockCityCommand implements Commandable {
    @Override
    public void executeCommand(GameCore game, Object target) {
        Commands.resetPathCommand.executeCommand(game, target);
        game.hand.setBlockCardState(true);
        game.hand.setCardUsability(false);
    }
}
