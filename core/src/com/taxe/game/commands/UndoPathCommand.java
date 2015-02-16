package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.IntermediatePoint;
import com.taxe.game.nodes.Node;
import com.taxe.game.nodes.NodeStates;
import com.taxe.game.tracks.Track;

/**
 * Undoes the path up to the current node.
 */
public class UndoPathCommand implements Commandable {

    /**
     * @param game instance of game
     * @param target node up to which path must be undone
     * @throws IllegalArgumentException if target not instance of node
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof Node)) {
            throw new IllegalArgumentException("target must be an instance of Node");
        }
        if (target instanceof IntermediatePoint)
            return;

        // Marking every node up to selected as unavailable for path selection
        Node selected = (Node) target;
        while (game.getSelectedPath().getLast() != selected) {
            Node current = game.getSelectedPath().removeLast();
            for (Track t : game.getMap().getTracksWith(current)) {
                boolean allSelected = true;
                for (Node n : t.getNodes()) {
                    if (n != current)
                        allSelected &= (n.getState() == NodeStates.SELECTED || n.getState() == NodeStates.HIGHLIGHTED);
                }
                if (allSelected) {
                    for (Node n : t.getNodes()) {
                        n.setState(NodeStates.ORIGINAL);
                    }
                }
            }
        }

        // Neighbours of the current selected node become available for path selection
        for (Track t : game.getMap().getTracksWith(selected)) {
            boolean alreadySelected = false;
            for (Node n : t.getNodes())
                alreadySelected |= (n.getState() == NodeStates.SELECTED);
            if (!alreadySelected) {
                for (Node n : t.getNodes())
                    if (n.getState() == NodeStates.ORIGINAL)
                        n.setState(NodeStates.HIGHLIGHTED);
            }
        }
        // Adding current selected node to path
        selected.setState(NodeStates.SELECTED);
    }
}
