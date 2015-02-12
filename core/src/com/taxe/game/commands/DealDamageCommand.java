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
     * @param target instance of player whose influence is being used to calculate damage on enemy's homebase.
     * @throws IllegalArgumentException if target not instance of Player
     */
    public void executeCommand(GameCore game, Object target) throws IllegalArgumentException {
        if (!(target instanceof Player)) {
            throw new IllegalArgumentException("target not instance of Player");
        }

        // Calculate player's influence
        Player p = (Player) target;
        float influenceSum = 0;
        for(City c : game.getMap().getCities()) {
            influenceSum += c.getInfluence(game.getPlayers().indexOf(target));
        }

        // TODO: Remove this for loop
        for(Player p2 : game.getPlayers()) {
            if(!(p.equals(p2))) {
                //Deal damage to enemy's base
                Homebase h = p2.getHomebase();
                h.changeHealthBy(- (int) (10*influenceSum));
                game.getGui().getHud().getHealthbar(p).setPercentage((float) h.getHealth() / h.getMaxHealth());

                // If homebase is destroyed, end game
                if (h.getHealth() == 0) {
                    Commands.endGameCommand.executeCommand(game, this);
                }
            }
        }
    }

}
