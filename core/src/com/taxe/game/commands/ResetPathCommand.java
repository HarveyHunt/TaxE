package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.Player;
import com.taxe.game.nodes.Node;
import com.taxe.game.nodes.NodeStates;
import com.taxe.game.trains.Train;
import com.taxe.game.trains.TrainStates;

/**
 * Created by vlad on 11/01/15.
 */
public class ResetPathCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        Player p = game.getActivePlayer();
        for (Train t : p.getTrains()) {
            t.setState(TrainStates.ACTIVE);
        }
        for (Node n : game.getMap().getNodes()) {
            n.setState(NodeStates.ORIGINAL);
        }
        game.getSelectedPath().clear();

        game.getGui().getHUD().hidePathButtons();
    }

}
