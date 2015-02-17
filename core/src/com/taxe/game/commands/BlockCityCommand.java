package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

/**
 * Created by henry on 09/02/15.
 */
public class BlockCityCommand implements Commandable {
    @Override
    public void executeCommand(GameCore game, Object target) {
        System.out.println("Block Fired");
        Commands.resetPathCommand.executeCommand(game, target);
        game.hand.setBlockCardState(true);
        game.hand.setCardUsability(false);
    }
}
