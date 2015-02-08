package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.IntermediatePoint;
import com.taxe.game.nodes.Node;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.NodeStates;
import com.taxe.game.tracks.Track;

/**
 * Continues train path which was already started.
 */
public class ContinuePathCommand implements Commandable {

    /**
     * @param game instance of game
     * @param target new node added to the path
     * @throws IllegalArgumentException if target not instance of Node
     */
    public void executeCommand(GameCore game, Object target) {
        if (!(target instanceof Node)) {
            throw new IllegalArgumentException("target not instance of Node");
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
            boolean alreadySelected = false;
            City c;
            for (Node n : t.getNodes()) {
                alreadySelected |= (n.getState() == NodeStates.SELECTED);
                /* If the city is locked, use a naughty hack to stop it from being selected. */
                if (n instanceof City) {
                    alreadySelected |= ((City) n).locked;
                }
            }
            if (!alreadySelected) {
                for (Node n : t.getNodes())
                    n.setState(NodeStates.HIGHLIGHTED);
            }
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
