package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;

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
    public void executeCommand(GameCore game,Object target) throws IllegalArgumentException {
        if(!(target instanceof City)){
            throw new IllegalArgumentException("target not instance of City");
        }
        city.locked = false;
    }
}

