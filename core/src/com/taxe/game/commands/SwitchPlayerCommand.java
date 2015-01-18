package com.taxe.game.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.taxe.game.GameCore;
import com.taxe.game.gui.GuiTextures;
import com.taxe.game.util.Coordinate;

/**
 * Created by vlad on 12/01/15.
 */
public class SwitchPlayerCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {

        Commands.activatePlayerCommand.executeCommand(game, game.nextActivePlayer());
        game.switchActivePlayer();

        Texture texture;
        if (game.getActivePlayer() == game.getPlayers().get(0)) {
            texture = GuiTextures.PLAYER_1_TURN_START;
        } else {
            texture = GuiTextures.PLAYER_2_TURN_START;
        }
        game.getGui().newNotification(texture, new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), 2);
    }

}
