package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;

/**
 * Block off a city for a random amount of turns.
 */
public class BlockCityCommand implements Commandable {
    /**
     * @param game   Instance of GameCore.
     * @param target A city that shall be locked.
     * @throws IllegalArgumentException if target is not an instance of City
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof City)) {
            throw new IllegalArgumentException("target not instance of City");
        }
        Commands.resetPathCommand.executeCommand(game, target);
        game.hand.setBlockCardState(true);
        game.hand.setCardUsability(false);
    }
}
