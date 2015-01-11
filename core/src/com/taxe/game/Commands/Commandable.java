package com.taxe.game.Commands;

import com.taxe.game.GameCore;

/**
 * Created by vlad on 11/01/15.
 */
public interface Commandable {

    public void executeCommand(GameCore game, Object o);

}
