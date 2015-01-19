package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.IntermediatePoint;
import com.taxe.game.trains.Train;
import com.taxe.game.trains.TrainStates;

/**
 * Starts the path on the node of the selected train.
 */
public class StartPathCommand implements Commandable {

    /**
     *
     * @param game instance of game
     * @param target train that was clicked.
     * @throws IllegalArgumentException if target not instance of train
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof Train)) {
            throw new IllegalArgumentException("target must be an instance of Train");
        }
        Commands.resetPathCommand.executeCommand(game, target);
        Train t = (Train) target;
        t.setState(TrainStates.SELECTED);
        if (!(t.getNode() instanceof IntermediatePoint)) {
            Commands.continuePathCommand.executeCommand(game, t.getNode());
            game.getGui().getHud().showPathButtons();
        }
    }

}
