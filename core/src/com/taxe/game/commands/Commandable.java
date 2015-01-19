package com.taxe.game.commands;

import com.taxe.game.GameCore;

/**
 * Specifies methods that all commands must implement.
 */
public interface Commandable {

    public void executeCommand(GameCore game, Object o);

}
