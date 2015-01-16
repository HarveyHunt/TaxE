package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Nodes.NodeStates;
import com.taxe.game.Player;
import com.taxe.game.Trains.Train;
import com.taxe.game.Trains.TrainStates;

/**
 * Created by vlad on 11/01/15.
 */
public class ResetPathCommand implements Commandable {

    public static void executeCommand(GameCore game, Object target) {
        Player p = game.getActivePlayer();
        for (Train t : p.getTrains()) {
            t.setState(TrainStates.ACTIVE);
        }
        for (Node n : game.getMap().getNodes()) {
            n.setState(NodeStates.ORIGINAL);
        }
        game.getSelectedPath().clear();
    }

}
