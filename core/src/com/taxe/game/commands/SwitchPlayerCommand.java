package com.taxe.game.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.taxe.game.GameCore;
import com.taxe.game.gui.GuiTextures;
import com.taxe.game.nodes.City;
import com.taxe.game.util.Coordinate;

import java.util.Random;

/**
 * Switches players turns.
 */
public class SwitchPlayerCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {

        City lockableCity = null;
        Random rand = new Random();

        Commands.activatePlayerCommand.executeCommand(game, game.nextActivePlayer());
        game.switchActivePlayer();

        game.hand.setCardUsability(true);
        game.hand.updateCardLabels(game);

        Texture texture;
        if (game.getActivePlayer() == game.getPlayers().get(0))
            texture = GuiTextures.PLAYER_1_TURN_START;
        else
            texture = GuiTextures.PLAYER_2_TURN_START;

        game.getGui().createNotification(texture, new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), 1);

        while (lockableCity == null) {
            City c = game.getMap().getCities().get(rand.nextInt(
                    game.getMap().getCities().size()));
            if (!c.locked)
                lockableCity = c;
        }

        for (City c : game.getMap().getCities())
            if (c.locked && rand.nextInt(3) == 0)
                Commands.unlockCityCommand.executeCommand(game, c);

        if (rand.nextInt(5) == 0)
            Commands.lockCityCommand.executeCommand(game, lockableCity);
    }
}
