package com.taxe.game.Resources;

import com.taxe.game.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents players' influence in the game.
 * Having more influence gives different passive rewards and is a sure path to victory.
 */
public class Influence {
    private HashMap<Player, Double> influences;

    public Influence(List<Player> players) {
        this.influences = new HashMap<>();
        for (Player p : players) {
            influences.put(p, 1.0 / players.size());
        }
    }

    /**
     *
     * @param player
     * @param delta Must be in range 0..1
     */
    public void changeInfluenceBy(Player player, double delta) {
        // Updating influence of the player and making sure it is in range 0..1
        double initial = getInfluence(player);
        double updated = (influences.size() > 1) ? Double.min(Double.max(0, initial + delta), 1) : 1;
        influences.put(player, updated);

        // Adjust influences of all other players accordingly, such that all percentages sum up to 1
        double oldRemaining = 1.0 - initial;
        double newRemaining = 1.0 - updated;
        for (Map.Entry<Player, Double> entry : influences.entrySet()) {
            if (entry.getKey() != player) {
                entry.setValue(entry.getValue() / oldRemaining * newRemaining);
            }
        }
    }

    public double getInfluence(Player player) {
        return influences.get(player);
    }

}
