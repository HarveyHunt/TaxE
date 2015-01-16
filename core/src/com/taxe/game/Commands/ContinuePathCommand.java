package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Nodes.IntermediatePoint;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Nodes.NodeStates;
import com.taxe.game.Tracks.Track;

/**
 * Created by vlad on 11/01/15.
 */
public class ContinuePathCommand implements Commandable {

    public static void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Node)) {
            throw new IllegalArgumentException("target must be an instance of Node");
        }
        if (target instanceof IntermediatePoint)
            return;
        Node current = (Node) target;
        Node previous = game.getSelectedPath().peekLast();

        // HIGHLIGHTED neighbours of the last selected node become unavailable for path selection
        if (previous != null) {
            for (Track t : game.getMap().getTracksWith(previous)) {
                for (Node n : t.getNodes())
                    if (n.getState() == NodeStates.HIGHLIGHTED)
                        n.setState(NodeStates.ORIGINAL);
            }
        }

        // Neighbours of the current selected node become available for path selection
        for (Track t : game.getMap().getTracksWith(current)) {
            for (Node n : t.getNodes())
                n.setState(NodeStates.HIGHLIGHTED);
        }

        // Track between previous and current selected nodes gets marked as SELECTED
        Track track = game.getMap().getTrackWith(previous, current);
        if (track != null) {
            for (Node n : track.getNodes()) {
                n.setState(NodeStates.SELECTED);
            }
        }

        // Adding current selected node to path
        current.setState(NodeStates.SELECTED);
        game.getSelectedPath().addLast(current);
    }

}
