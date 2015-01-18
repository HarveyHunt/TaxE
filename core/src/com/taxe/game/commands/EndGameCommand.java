package com.taxe.game.commands;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.GameCore;
import com.taxe.game.gui.GUI;
import com.taxe.game.player.Player;

/**
 * Created by Owen on 18/01/2015.
 */
public class EndGameCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        int winner;
        winner = (game.getActivePlayer() == game.getPlayers().get(0)) ? 1 : 0;

        GUI gui = game.getGui();
        gui.createGameEndMenu(winner);
        gui.getHUD().lockButtons();
        gui.getHUD().hidePathButtons();
        for (Player player : game.getPlayers()) {
            player.setTrainsTouchable(Touchable.disabled);
        }
        System.out.println("HI");
    }

}
