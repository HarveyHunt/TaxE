package com.taxe.game.commands;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.tasks.Task;
import com.taxe.game.trains.Train;
import com.taxe.game.util.Pair;

/**
 * Attempt to unload Cargo into the train that has stopped on City.
 */
public class UnloadCargoCommand implements Commandable {
    /**
     * @param game instance of GameCore
     * @param target A Pair of the form: (City, Task).
     * @throws IllegalArgumentException if the Pair isn't of the form
     * (City, Task).
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof Pair))
            throw new IllegalArgumentException("target not instance of Pair");
        if (!(((Pair) target).left instanceof City))
            throw new IllegalArgumentException("target.left not instance of City");
        if (!(((Pair) target).right instanceof Task))
            throw new IllegalArgumentException("target.right not instance of Task");

        City city = (City) ((Pair) target).left;
        Task task = (Task) ((Pair) target).right;

        /*
         * By the time we get to here, we know that a train is on a target city
         * and the city is the end point of a task. We just need to check that
         * the cargo type is correct and the reward the player for their work.
         */
        for (Train t : game.getActivePlayer().getTrains())
            if (t.getCargo().getId().equals(task.getCargo().getId())) {
                city.changeInfluenceBy(game.getPlayers().indexOf(target), 0.1f);

                // TODO: Check that ((Player) target) doesn't just return a
                // memory address. If it does, implement a name for players.
                Label label = new Label("Player " + (game.getActivePlayer().id + 1)
                        + " has completed a task",
                        new Label.LabelStyle(new BitmapFont(), Color.GREEN));
                label.setAlignment(Align.center);

                game.getGui().getNotificationBox().addLabel(label, 5.0f);

                t.unload();
                game.getGui().getInfoDisplay().removeTask(task);
                game.getTasks().remove(task);
            }
    }
}