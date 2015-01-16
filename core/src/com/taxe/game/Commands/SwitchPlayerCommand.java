package com.taxe.game.Commands;

import com.taxe.game.GameCore;

/**
 * Created by vlad on 12/01/15.
 */
public class SwitchPlayerCommand implements Commandable {

    public static void executeCommand(GameCore game, Object target) {

        new ActivatePlayerCommand().executeCommand(game, game.nextActivePlayer());
        game.switchActivePlayer();
        new ResetPathCommand().executeCommand(game, null);

    }

}
