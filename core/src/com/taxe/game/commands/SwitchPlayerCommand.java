package com.taxe.game.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.taxe.game.GameCore;
import com.taxe.game.ui.NotificationTextures;
import com.taxe.game.util.Coordinate;

/**
 * Created by vlad on 12/01/15.
 */
public class SwitchPlayerCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {

        Commands.activatePlayerCommand.executeCommand(game, game.nextActivePlayer());
        game.switchActivePlayer();

        Texture texture;
        CharSequence playerText;
        int player;
        if (game.getActivePlayer() == game.getPlayers().get(0)) {
            texture = NotificationTextures.PLAYER1_TURN;
            playerText = "Player 1's Turn";
            player = 0;
        } else {
            texture = NotificationTextures.PLAYER2_TURN;
            playerText = "Player 2's Turn";
            player = 1;
        }
        game.getGui().newNotification(texture, new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), 2);
        game.getGui().getHUD().setTurnText(playerText);
        game.getGui().getHUD().setResourcesText("Gold: " +
                        +game.getPlayers().get(player).getGold().getQuantity()
                        + "     Fuel: "
                        + game.getPlayers().get(player).getFuel().getUsedFuel()
                        + "/"
                        + game.getPlayers().get(player).getFuel().getFuelCap()
        );
    }

}
