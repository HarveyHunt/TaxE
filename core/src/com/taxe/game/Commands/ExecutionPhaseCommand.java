package com.taxe.game.Commands;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.taxe.game.Tracks.Sleeper;
import com.taxe.game.Util.Coordinate;
import com.taxe.game.GameCore;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Trains.Train;

import java.util.Deque;

/**
 * Created by vlad on 12/01/15.
 */
public class ExecutionPhaseCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        for (Train train: game.getActivePlayer().getTrains()) {
            Deque <Node> nodes = train.getPathNodes();
            Deque <Sleeper> coordinates = train.getPathCoordinates();
            if (nodes != null) {
                Node n = nodes.pollFirst();
                Sleeper c = null;
                SequenceAction seq = new SequenceAction();
                for (int i = 0; i < train.getSpeed(); i++) {
                    n = (i == train.getSpeed() - 1) ? nodes.peekFirst(): nodes.pollFirst();
                    if (n != null) {
                        do {
                            c = coordinates.removeFirst();
                            seq.addAction(Actions.moveTo((float) c.getX(), (float) c.getY(), 0.25f));
                            System.out.println(c.getX() + ", " + c.getY());
                        } while (! c.isEnding());
                    }
                }
                train.addAction(seq);
                if (n != null) {
                    train.setNode(n);
                    //train.setCoordinate(n.getCoordinate());
                }
            }
        }
    }

}
