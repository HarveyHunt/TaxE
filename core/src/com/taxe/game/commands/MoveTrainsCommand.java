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
 * Created by vlad on 12/01/15.
 */
public class MoveTrainsCommand implements Commandable {

    public void executeCommand(GameCore game, Object target) {
        Commands.resetPathCommand.executeCommand(game, null);

        for (Train train : game.getActivePlayer().getTrains()) {
            Deque<Node> nodes = train.getPathNodes();
            Deque<Sleeper> sleepers = train.getPathSleepers();
            System.out.println(nodes);
            SequenceAction seq = new SequenceAction();
            Node n = train.getNode();
            if (nodes != null && !nodes.isEmpty()) {
                Sleeper s;
                System.out.println(n);
                for (int i = 0; i < train.getSpeed() && !nodes.isEmpty(); i++) {
                    n = nodes.pollFirst();
                    System.out.println(n);
                    do {
                        s = sleepers.removeFirst();
                        seq.addAction(Actions.moveTo(s.getX(), s.getY(), 0.05f));
                        System.out.println(s.getX() + ", " + s.getY());
                    } while (!s.isEnding());
                }
            }
            Action end = new Action() {
                @Override
                public boolean act(float delta) {
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
