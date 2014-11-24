package com.taxe.game;

import java.util.HashMap;

/**
 * Created by Owen on 19/11/2014.
 */
public class Influence {
    private int maxInfluence;
    private HashMap<Player, Integer> influences;

    public Influence(HashMap <Player, Integer> influences, int maxInfluence) {
        this.influences = influences;
        this.maxInfluence = maxInfluence;
    }

    public void changeInfluenceBy(Player player, int delta) {
        int newValue = getInfluence(player) + delta;

        // Check that the new value is not too high or too low
        if (newValue < 0){
            newValue = 0;
        } else if(newValue > maxInfluence){
            newValue = maxInfluence;
        }

        // Set the new value
        influences.put(player, newValue);

        // Check if this has pushed the total influence over the cap
        int sum = sumInfluences();
        if (sum > maxInfluence){
            // Reduce the other players influences to resolve the difference
            for (Player key : influences.keySet()){
                if(key != player){
                    influences.put(key, influences.get(key) - (sum - maxInfluence) / (influences.size() - 1));
                }
            }
        }
    }

    public int getFreeInfluence(){
        return maxInfluence - sumInfluences();
    }

    private int sumInfluences(){
        int sum = 0;
        for (int value : influences.values()){
            sum += value;
        }
        return sum;
    }

    public int getInfluence(Player player){
        return influences.get(player);
    }

    public int getMaxInfluence(){
        return maxInfluence;
    }

}

