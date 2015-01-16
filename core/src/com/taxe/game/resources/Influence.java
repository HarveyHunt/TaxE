package com.taxe.game.resources;

import com.taxe.game.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents players' relative influence (in range 0..1, think %) in cities.
 * Having more influence gives rewards and is a sure path to victory.
 */
public class Influence {
    private HashMap<Player, Double> influences;

    /**
     * Creates a new instance of influence resource, such that each player receives an equal initial amount of influence.
     * @param players list of players
     */
    public Influence(List<Player> players) {
        this.influences = new HashMap<>();
        for (Player p : players) {
            influences.put(p, 1.0 / players.size());
        }
        validateInfluence();
    }

    /**
     * Changes influence of a player by a set amount; influences of other players will be readjusted accordingly.
     * @param player player whose influence will be changing.
     * @param delta  by how much influence is changed
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
        validateInfluence();
    }

    /**
     * Returns relative influence of a player
     * @param player player whose influence want to know
     * @return relative influence of a player, in range 0..1
     */
    public double getInfluence(Player player) {
        return influences.get(player);
    }

    /**
     * Checks if influence satisfies the constraints.
     * @throws AssertionError if influence of players isn't in range 0..1 or if sum of influences of all players != 1
     */
    public void validateInfluence() throws AssertionError {
        double sum = 0;
        for (double i: influences.values()) {
            assert (i >= 0 && i <= 1);
            sum += i;
        }
        assert Math.abs(1 - sum) < 0.000001;
    }

}
