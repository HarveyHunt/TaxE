package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Defines actions that need to happen when player's trains stop moving. Namely, unlocks buttons, switches player and
 * deals damage to the opponent.
 */
public class EndMovementCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        game.getGui().getHud().unlockButtons();
        Commands.switchPlayerCommand.executeCommand(game, null);
        Commands.dealDamageCommand.executeCommand(game, game.getActivePlayer());
    }

}
