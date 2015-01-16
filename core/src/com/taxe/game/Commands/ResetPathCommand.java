package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Player;
import com.taxe.game.Trains.Train;
import com.taxe.game.Trains.TrainStates;
import com.taxe.game.nodes.Node;
import com.taxe.game.nodes.NodeStates;

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
