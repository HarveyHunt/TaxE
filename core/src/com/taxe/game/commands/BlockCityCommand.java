package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;

/**
 * Block off a city for a random amount of turns.
 */
public class BlockCityCommand implements Commandable {
    /**
     * @param game   Instance of GameCore.
     * @param target unused
     */
    public void executeCommand(GameCore game, Object target) {
        Commands.resetPathCommand.executeCommand(game, target);
        game.hand.setBlockCardState(true);
        game.hand.setCardUsability(false);
    }
}
