package com.taxe.game.Commands;

import com.taxe.game.Commands.Command;
import com.taxe.game.GameCore;

/**
 * Created by vlad on 11/01/15.
 */
public class EndTurnCommand extends Command {

    public void executeCommand(GameCore game) {
        System.out.println("Time to end turn!\n");
    }
}
