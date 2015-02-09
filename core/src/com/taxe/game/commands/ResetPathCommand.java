package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.player.Player;
import com.taxe.game.nodes.Node;
import com.taxe.game.nodes.NodeStates;
import com.taxe.game.trains.Train;
import com.taxe.game.trains.TrainStates;

/**
 * Reset current selected paths.
 */
public class ResetPathCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        // Reset the state of trains and nodes
        Player p = game.getActivePlayer();
        for (Train t : p.getTrains()) {
            t.setState(TrainStates.ACTIVE);
        }
        for (Node n : game.getMap().getNodes()) {
            if (n instanceof City && ((City) n).locked == false)
                n.setState(NodeStates.ORIGINAL);
        }

        // Clear path and hide buttons
        game.getSelectedPath().clear();
        game.getGui().getHud().hidePathButtons();
    }

}
