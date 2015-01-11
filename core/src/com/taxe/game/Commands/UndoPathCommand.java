package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Nodes.IntermediatePoint;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Textures;
import com.taxe.game.Track;

/**
 * Created by vlad on 11/01/15.
 */
public class UndoPathCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Node)) {
            throw new IllegalArgumentException("target must be an instance of Node");
        }
        if (target instanceof IntermediatePoint)
            return;

        Node selected = (Node) target;

        // Marking every node up to selected as unavailable for path selection
        while (game.lastInSelectedPath() != selected) {
            Node current = game.lastInSelectedPath();
            for (Track t : game.getMap().getTracksWith(current)) {
                for (Node n : t.getPath()) {
                    n.setState(Textures.ORIGINAL);
                }
            }
            game.removeLastInSelectedPath();
        }

        // Including selected node in current selected path
        new ContinuePathCommand().executeCommand(game, selected);


    }
}
