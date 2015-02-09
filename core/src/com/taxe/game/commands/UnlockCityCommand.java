package com.taxe.game.commands;

import com.badlogic.gdx.Gdx;
import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.util.Coordinate;

/**
 * Unlock a city so that trains can travel over it.
 */
public class UnlockCityCommand implements Commandable {
    /**
     *
     * @param game instance of GameCore
     * @param target The object to unlock (this needs to be a city)
     * @throws IllegalArgumentException if the target isn't a City.
     */
    public void executeCommand(GameCore game,Object target) throws IllegalArgumentException {
        if(!(target instanceof City)){
            throw new IllegalArgumentException("target not instance of City");
        }
        City c = (City)target;
        c.locked = false;
        game.getGui().createTextNotification("City " + c.getName() + " has been unlocked",
                new Coordinate(Gdx.graphics.getWidth() / 2,
                        Gdx.graphics.getHeight() / 2), 1);
    }
}

