package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.Node;
import com.taxe.game.tracks.Sleeper;
import com.taxe.game.tracks.Track;
import com.taxe.game.trains.Train;
import com.taxe.game.trains.TrainStates;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

/**
 * Saves the current selected path and assigns it to train.
 */
public class SavePathCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        ArrayDeque<Node> nodes = new ArrayDeque<>();
        ArrayDeque<Sleeper> sleepers = new ArrayDeque<>();
        Node current, previous = null;
        while (!game.getSelectedPath().isEmpty()) {
            current = game.getSelectedPath().removeFirst();
            // Adding nodes and coordinates on track going from previous to current
            // Note: track can contain nodes in reverse order; correct order: previous -> current
            if (previous != null) {
                Track track = game.getMap().getTrackWith(current, previous);
                List<Node> ns = track.getNodes();
                List<Sleeper> cs = track.getSleepers();
                if (!ns.isEmpty() && ns.get(0) == current) {
                    Collections.reverse(ns);
                    Collections.reverse(cs);
                }
                for (Node n : ns) {
                    if (!nodes.contains(n)) {
                        nodes.addLast(n);
                    }
                }
                for (Sleeper c : cs) {
                    if (!sleepers.contains(c)) {
                        sleepers.addLast(c);
                    }
                }
            }
            previous = current;
        }

        // Removing the current node from the path, such that the train would start moving immediately towards the next node
        for (Train t : game.getActivePlayer().getTrains()) {
            if (t.getState() == TrainStates.SELECTED) {
                nodes.pollFirst();
                sleepers.pollFirst();
                t.setPath(nodes, sleepers);
            }
        }
        new ResetPathCommand().executeCommand(game, null);
    }

}
