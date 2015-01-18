package com.taxe.game.inputhandling;

import com.taxe.game.GameCore;

/**
 * Specifies methods that must be implemented by objects that can be clicked.
 */
public interface Clickable {

    /**
     * Specifies actions, changes and commands which must be made after object was clicked.
     *
     * @param game instance of current game.
     */
    public void clicked(GameCore game);

}
