package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Exits to main menu.
 */
public class ExitToMenuCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        game.getMain().exitToMenu();
    }


}
