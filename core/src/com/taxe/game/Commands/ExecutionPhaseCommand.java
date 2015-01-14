package com.taxe.game.Commands;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.taxe.game.GameCore;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Tracks.Sleeper;
import com.taxe.game.Trains.Train;

import java.util.Deque;

/**
 * Created by vlad on 12/01/15.
 */
public class ExecutionPhaseCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        for (Train train : game.getActivePlayer().getTrains()) {
            Deque<Node> nodes = train.getPathNodes();
            Deque<Sleeper> sleepers = train.getPathCoordinates();
            if (nodes != null) {
                Node n = nodes.pollFirst();
                Sleeper s = null;
                SequenceAction seq = new SequenceAction();
                for (int i = 0; i < train.getSpeed(); i++) {
                    n = (i == train.getSpeed() - 1) ? nodes.peekFirst() : nodes.pollFirst();
                    if (n != null) {
                        do {
                            s = sleepers.removeFirst();
                            seq.addAction(Actions.moveTo(s.getX(), s.getY(), 0.1f));
                            System.out.println(s.getX() + ", " + s.getY());
                        } while (!s.isEnding());
                    }
                }
                train.addAction(seq);
                if (n != null) {
                    train.setNode(n);
                }
            }
        }
    }

}
