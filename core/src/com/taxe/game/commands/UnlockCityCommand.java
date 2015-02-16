package com.taxe.game.commands;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.NodeStates;

/**
 * Unlock a city so that trains can travel over it.
 */
public class UnlockCityCommand implements Commandable {
    /**
     * @param game   instance of GameCore
     * @param target The object to unlock (this needs to be a city)
     * @throws IllegalArgumentException if the target isn't a City.
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof City)) {
            throw new IllegalArgumentException("target not instance of City");
        }
        City c = (City) target;

        c.locked = false;
        c.setState(NodeStates.ORIGINAL);

        Label label = new Label(c.getId() + " has been unlocked",
                new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        label.setAlignment(Align.center);

        game.getGui().getNotificationBox().addLabel(label, 5.0f);
    }
}

