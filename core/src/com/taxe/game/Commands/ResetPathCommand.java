package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Textures;
import com.taxe.game.Trains.Train;

/**
 * Created by vlad on 11/01/15.
 */
public class ResetPathCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Train)) {
            throw new IllegalArgumentException("target must be an instance of Train");
        }
        Train t = (Train) target;
        t.setState(Textures.HIGHLIGHTED);
        for (Node n : game.getMap().getNodes()) {
            n.setState(Textures.ORIGINAL);
        }
        game.clearSelectedPath();
    }

}
