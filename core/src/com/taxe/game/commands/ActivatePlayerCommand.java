package com.taxe.game.commands;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.player.Player;
import com.taxe.game.tasks.Task;
import com.taxe.game.trains.Train;
import com.taxe.game.trains.TrainStates;

import java.util.Iterator;

/**
 * Activates trains of a given player and disables trains of all other players.
 */
public class ActivatePlayerCommand implements Commandable {

    /**
     * @param game instance of game
     * @param target player that must be activated
     * @throws IllegalArgumentException if target is not an instance of Player
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("target not instance of Player");
        }

        // Disable trains of all players
        for (Player p : game.getPlayers()) {
            for (Train t : p.getTrains()) {
                t.setState(TrainStates.INACTIVE);
            }
        }

        // Enable trains of given player
        for (Train t : ((Player) target).getTrains())
            t.setState(TrainStates.ACTIVE);

        // Complete or delete tasks before adding a new one.
        for (Iterator<Task> iter = game.getTasks().iterator(); iter.hasNext();) {
            Task t = iter.next();
            
            if (t.isComplete((Player) target)) {
                t.getEndCity().changeInfluenceBy(game.getPlayers().indexOf(target), 0.1f);

                // TODO: Check that ((Player) target) doesn't just return a
                // memory address. If it does, implement a name for players.
                Label label = new Label("Player " + ((Player) target) +
                        " has completed a task",
                        new Label.LabelStyle(new BitmapFont(), Color.GREEN));
                label.setAlignment(Align.center);

                game.getGui().getNotificationBox().addLabel(label, 5.0f);

                game.getGui().getInfoDisplay().removeTask(t);
                iter.remove();

            } else if (t.getTasktime() == 0) {
                game.getGui().getInfoDisplay().removeTask(t);
                iter.remove();

                Label label = new Label(t + " has expired",
                        new Label.LabelStyle(new BitmapFont(), Color.RED));
                label.setAlignment(Align.center);

                game.getGui().getNotificationBox().addLabel(label, 5.0f);
            }
            t.completeTurn();
            game.getGui().getInfoDisplay().updateTurns();
        }

        // Increase player's gold based on influence.
        // TODO: This could be made more interesting.
        for (City c: game.getMap().getCities()) {
            int goldToAdd = Math.round(100 * c.getInfluence(game.getPlayers().indexOf(target)));
            ((Player) target).changeGold(goldToAdd);
        }

        if (game.getTasks().size() < 5) {
            Task t = game.taskFactory.generateTask();
            game.getTasks().add(t);
            game.getGui().getInfoDisplay().addTask(t);
        }
    }
}
