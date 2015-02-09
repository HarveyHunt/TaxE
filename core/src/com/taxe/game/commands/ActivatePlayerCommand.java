package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.player.Player;
import com.taxe.game.tasks.Task;
import com.taxe.game.trains.Train;
import com.taxe.game.trains.TrainStates;

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
        for (Train t : ((Player) target).getTrains()) {
            t.setState(TrainStates.ACTIVE);
        }

        // Complete tasks before adding a new one.
        for (Task task : game.getTasks()) {
            if (task.isComplete((Player) target)) {
                task.getEndCity().changeInfluenceBy((Player) target, 0.1f);
            }
        }

        // Increase player's gold based on influence.
        for (City c: game.getMap().getCities()) {
            int goldToAdd = (int)Math.round(100 * c.getInfluence(((Player) target)));
            ((Player) target).setGold(((Player) target).getGold() + goldToAdd);
        }

        if (game.getTasks().size() < 5)
            game.getTasks().add(game.taskFactory.generateTask());
    }
}
