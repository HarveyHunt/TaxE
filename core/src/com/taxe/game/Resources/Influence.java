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
    private final double maxInfluence;
    private HashMap<Player, Double> influences;

    public Influence(List<Player> players, double maxInfluence) {
        this.maxInfluence = maxInfluence;
        this.influences = new HashMap<>();
        double influencePerPlayer = maxInfluence / players.size();
        for (Player p : players) {
            influences.put(p, influencePerPlayer);
        }
    }

    public void changeInfluenceBy(Player player, double delta) {
        // Updating influence of the player and making sure it doesn't exceed limits
        double updated = getInfluence(player) + delta;
        updated = Double.max(Double.min(updated, maxInfluence), 0.0);
        influences.put(player, updated);

        // Adjust influences of all other players accordingly
        double influencePerPlayer = (maxInfluence - updated) / (influences.size() - 1);
        for (Map.Entry<Player, Double> entry : influences.entrySet()) {
            if (entry.getKey() != player)
                entry.setValue(influencePerPlayer);
        }
    }

    public double getInfluence(Player player) {
        return influences.get(player);
    }

    public double getMaxInfluence() {
        return maxInfluence;
    }

}
