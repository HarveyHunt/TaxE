package com.taxe.game.commands;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.GameCore;
import com.taxe.game.player.Player;

/**
 * Created by Owen on 18/01/2015.
 */
public class EndGameCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        int winner;
        if (game.getActivePlayer() == game.getPlayers().get(0)) {
            winner = 1;
        } else {
            winner = 0;
        }
        game.getGui().createGameEndMenu(winner);
        game.getGui().getHUD().lockButtons();
        game.getGui().getHUD().hidePathButtons();
        for (Player player : game.getPlayers()) {
            player.setTrainsTouchable(Touchable.disabled);
        }
    }

}
