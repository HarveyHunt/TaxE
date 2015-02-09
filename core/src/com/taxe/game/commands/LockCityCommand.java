package com.taxe.game.commands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.NodeStates;
import com.taxe.game.util.Coordinate;

/**
 * Lock a city so that trains can't travel over it.
 */
public class LockCityCommand implements Commandable {
    /**
     *
     * @param game instance of GameCore
     * @param target The object to unlock (this needs to be a city)
     * @throws IllegalArgumentException if the target isn't a City.
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if(!(target instanceof City)){
            throw new IllegalArgumentException("target not instance of City");
        }
        City c = (City)target;
        c.locked = true;
        c.setState(NodeStates.LOCKED);

        Label label = new Label("City " + c.getId() + " has been locked",
                new Label.LabelStyle(new BitmapFont(), Color.RED));
        label.setAlignment(Align.center);

        game.getGui().createTextNotification(label, new Coordinate(
                Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2), 1);
    }
}

