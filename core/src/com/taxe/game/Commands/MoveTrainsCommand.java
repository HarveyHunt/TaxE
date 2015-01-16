package com.taxe.game.Commands;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.taxe.game.GameCore;
import com.taxe.game.Tracks.Sleeper;
import com.taxe.game.Trains.Train;
import com.taxe.game.nodes.Node;

import java.util.Deque;

/**
 * Created by vlad on 12/01/15.
 */
public class MoveTrainsCommand implements Commandable {

    public static void executeCommand(GameCore game, Object target) {
        for (Train train : game.getActivePlayer().getTrains()) {
            Deque<Node> nodes = train.getPathNodes();
            Deque<Sleeper> sleepers = train.getPathSleepers();
            System.out.println(nodes);
            if (nodes != null && !nodes.isEmpty()) {
                Node n = null;
                Sleeper s = null;
                System.out.println(n);
                SequenceAction seq = new SequenceAction();
                for (int i = 0; i < train.getSpeed() && !nodes.isEmpty(); i++) {
                    n = nodes.pollFirst();
                    System.out.println(n);
                    do {
                        s = sleepers.removeFirst();
                        seq.addAction(Actions.moveTo(s.getX(), s.getY(), 0.05f));
                        System.out.println(s.getX() + ", " + s.getY());
                    } while (!s.isEnding());
                }
                train.addAction(seq);
                train.setNode(n);
            }
        }
    }

}
