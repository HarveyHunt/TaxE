package com.taxe.game.resources;

import com.taxe.game.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents players' relative influence (in range 0..1, think %) in cities.
 * <p>
 * If one player has all influence in the city, his influence will be 1, while influence of all other players will be 0.
 * If there are four players with equal influence, their values would all be 0.25. Sum of influences of all players must
 * always be equal to 1.
 * <p>
 * Having more influence gives rewards and is a sure path to victory.
 */
public class Influence {
    private HashMap<Player, Float> influences;

    /**
     * Creates a new instance of influence resource, such that each player receives an equal initial amount of
     * influence.
     *
     * @param players list of players.
     */
    public Influence(List<Player> players) {
        this.influences = new HashMap<>();
        for (Player p : players) {
            influences.put(p, 1f / players.size());
        }
        validateInfluence();
    }

    /**
     * Changes influence of a player by a set amount and recalculates influences of other players. This is done as
     * follows: <ol> <li>Player's current influence is initial = getInfluence(player).</li> <li>Player's new influence
     * is updated = initial + delta, clipped to 0 or 1, if the results is not in range  0..1 .</li> <li>Individual influence of
     * all other players pi is getInfluence(pi) / (1 - initial) * (1 - updated).</li> </ol>
     *
     * @param player player whose influence will be changing.
     * @param delta  by how much influence is changed
     */
    public void changeInfluenceBy(Player player, float delta) {
        // Updating influence of the player and making sure it is in range 0..1
        float initial = getInfluence(player);
        float updated = (influences.size() > 1) ? Math.min(Math.max(0, initial + delta), 1) : 1;
        influences.put(player, updated);

        // Adjust influences of all other players, such that all percentages sum up to 1
        float oldRemaining = 1f - initial;
        float newRemaining = 1f - updated;
        for (Map.Entry<Player, Float> entry : influences.entrySet()) {
            if (entry.getKey() != player) {
                entry.setValue(entry.getValue() / oldRemaining * newRemaining);
            }
        }
        validateInfluence();
    }

    /**
     * Returns influence of a player.
     *
     * @param player player whose influence want to know
     * @return relative influence of a player, in range 0..1
     */
    public float getInfluence(Player player) {
        return influences.get(player);
    }

    /**
     * Checks if influence satisfies the constraints.
     *
     * @throws RuntimeException if influence of players isn't in range 0..1 or if sum of influences of all players != 1
     */
    public void validateInfluence() throws RuntimeException {
        float sum = 0;
        for (float i : influences.values()) {
            if (i < 0) throw new RuntimeException("influence of a player < 0");
            if (i > 1) throw new RuntimeException("influence of a player > 1");
            sum += i;
        }
        if (Math.abs(1 - sum) > 0.000001) throw new RuntimeException("sum of influences != 1");
    }

}
