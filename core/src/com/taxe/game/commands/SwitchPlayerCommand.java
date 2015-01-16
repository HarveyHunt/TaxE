package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Created by vlad on 12/01/15.
 */
public class SwitchPlayerCommand implements Commandable {

    public static void executeCommand(GameCore game, Object target) {

        ActivatePlayerCommand.executeCommand(game, game.nextActivePlayer());
        game.switchActivePlayer();

    }

}
