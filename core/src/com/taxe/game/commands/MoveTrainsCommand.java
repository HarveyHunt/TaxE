package com.taxe.game.commands;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.taxe.game.GameCore;
import com.taxe.game.nodes.Node;
import com.taxe.game.tracks.Sleeper;
import com.taxe.game.trains.Train;

import java.util.Deque;

/**
 * Move active player's trains by train.getSpeed() nodes forward if they have saved path.
 */
public class MoveTrainsCommand implements Commandable {

    public void executeCommand(final GameCore game, Object target) {
        Commands.resetPathCommand.executeCommand(game, null);

        // Assign movement to each train separately
        for (final Train train : game.getActivePlayer().getTrains()) {
            Deque<Node> nodes = train.getPathNodes();
            Deque<Sleeper> sleepers = train.getPathSleepers();
            SequenceAction seq = new SequenceAction();
            Node n = train.getNode();

            // If trains path has some nodes in it
            if (nodes != null && !nodes.isEmpty()) {
                Sleeper s;
                // Remove next node from the list and add sleepers between previous node and that node
                for (int i = 0; i < train.getSpeed() && !nodes.isEmpty(); i++) {
                    n = nodes.pollFirst();
                    do {
                        s = sleepers.removeFirst();
                        seq.addAction(Actions.moveTo(s.getX(), s.getY(), 0.05f));
                    } while (!s.isEnding());
                }
            }
            // Check if all trains finished movement, and if yes, execute endMovementCommand
            Action end = new Action() {
                @Override
                public boolean act(float delta) {
                    for (Train t : game.getActivePlayer().getTrains())
                        if (t != train && t.getActions().size > 0)
                            return true;
                    Commands.endMovementCommand.executeCommand(game, game.getActivePlayer());
                    return true;
                }
            };
            seq.addAction(end);
            train.addAction(seq);
            train.setNode(n);
        }
        game.getGui().getHud().lockButtons();
    }
}
