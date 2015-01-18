package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Created by Owen on 18/01/2015.
 */
public class ExitToMenuCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        game.getMain().exitToMenu();
    }


}
