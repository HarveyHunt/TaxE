package com.taxe.game.commands;

import com.taxe.game.GameCore;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.Homebase;
import com.taxe.game.player.Player;

/**
 * Deals damage to the opponent's homebase.
 */
public class DealDamageCommand implements Commandable {

    /**
     *
     * @param game instance of game
     * @param target instance of game whose homebase is receiving damage
     * @throws IllegalArgumentException if target not instance of Player
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("target not instance of Player");
        }

        // Deal damage to player's homebase
        Player p = (Player) target;
        float influenceSum = 0;
        for(Player p2 : game.getPlayers()) {
            if(!(p.equals(p2))) {
                for(City c : game.getMap().getCities()) {
                    influenceSum += c.getInfluence(p2);
                }
            }
        }
        Homebase h = p.getHomebase();
        h.changeHealthBy(Math.round(-100 * influenceSum));
        game.getGui().getHud().getHealthbar(p).setPercentage((float) h.getHealth() / h.getMaxHealth());

        // If homebase is destroyed, end game
        if (h.getHealth() == 0) {
            Commands.endGameCommand.executeCommand(game, this);
        }
    }

}
