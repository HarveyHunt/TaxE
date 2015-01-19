package com.taxe.game.commands;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.GameCore;
import com.taxe.game.gui.Gui;
import com.taxe.game.player.Player;

/**
 * Shows the winner and allows to exit the game or to return to main menu.
 */
public class EndGameCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {

        // Determine winner
        int winner = (game.getActivePlayer() == game.getPlayers().get(0)) ? 1 : 0;

        // Hide and lock appropriate button, disable trains
        Gui gui = game.getGui();
        gui.getHud().lockButtons();
        gui.getHud().hidePathButtons();
        for (Player player : game.getPlayers()) {
            player.setTrainsTouchable(Touchable.disabled);
        }

        // Create GameEndMenu
        gui.createGameEndMenu(winner);
    }

}
