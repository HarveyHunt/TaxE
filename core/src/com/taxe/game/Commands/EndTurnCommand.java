package com.taxe.game.Commands;

import com.taxe.game.GameCore;

/**
 * Created by vlad on 11/01/15.
 */
public class EndTurnCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        System.out.println("Time to end turn!\n");
    }
}
