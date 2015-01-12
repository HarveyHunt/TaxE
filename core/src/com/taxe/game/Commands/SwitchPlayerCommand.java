package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Player;

/**
 * Created by vlad on 12/01/15.
 */
public class SwitchPlayerCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {

        new ActivatePlayerCommand().executeCommand(game, game.nextActivePlayer());
        game.switchActivePlayer();
        new ResetPathCommand().executeCommand(game, null);

    }

}
