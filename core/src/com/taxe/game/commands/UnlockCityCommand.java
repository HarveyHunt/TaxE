package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;


public class UnlockCityCommand implements Commandable {
    public void executeCommand(GameCore game,Object target) throws IllegalArgumentException {
        if(!(target instanceof City)){
            throw new IllegalArgumentException("target not instance of City");
        }
        city.locked = true;
    }
}

