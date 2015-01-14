package com.taxe.game.Commands;

import com.taxe.game.GameCore;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Tracks.Sleeper;
import com.taxe.game.Tracks.Track;
import com.taxe.game.Trains.Train;
import com.taxe.game.Trains.TrainStates;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

/**
 * Created by vlad on 12/01/15.
 */
public class SavePathCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        ArrayDeque<Node> nodes = new ArrayDeque<>();
        ArrayDeque<Sleeper> sleepers = new ArrayDeque<>();
        Node current = null, previous = null;
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
        for (Train t : game.getActivePlayer().getTrains()) {
            if (t.getState() == TrainStates.SELECTED) {
                t.setPath(nodes, sleepers);
            }
        }
        new ResetPathCommand().executeCommand(game, null);
    }

}
