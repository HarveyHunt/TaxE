package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Created by Owen on 18/01/2015.
 */
public class EndGameCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        game.getGui().createGameEndMenu(game.getActivePlayer().getId());
        game.getGui().getHUD().lockButtons();
    }

}
