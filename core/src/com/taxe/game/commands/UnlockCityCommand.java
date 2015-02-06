package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;

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
        c.locked = true;
    }
}

