package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.nodes.City;
import com.taxe.game.util.Pair;

/**
 * Attempt to load Cargo into the train that has stopped on City.
 */
public class LoadCargoCommand implements Commandable {
    /**
     * @param game instance of GameCore
     * @param target A Pair of the form: (City, Cargo).
     * @throws IllegalArgumentException if the Pair isn't of the form
     * (City, Cargo).
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof Pair))
            throw new IllegalArgumentException("target not instance of Pair");
        if (!(((Pair) target).left instanceof City))
            throw new IllegalArgumentException("target.left not instance of City");
        if (!(((Pair) target).right instanceof Cargo))
            throw new IllegalArgumentException("target.right not instance of Cargo");
    }
}