package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Created by Owen on 18/01/2015.
 */
public class EndGameCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        int winner;
        if (game.getActivePlayer() == game.getPlayers().get(0)) {
            winner = 0;
        } else {
            winner = 1;
        }
        game.getGui().createGameEndMenu(winner);
        game.getGui().getHUD().lockButtons();
    }

}
