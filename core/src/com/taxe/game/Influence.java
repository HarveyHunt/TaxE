package com.taxe.game;

/**
 * Created by Owen on 19/11/2014.
 */
public class Influence {
    private int totalInfluence;
    private int[] players;

    public Influence(int totalInfluence, int player1Influence, int player2Influence) {
        this.totalInfluence = totalInfluence;
        players = new int[2];
        this.players[0] = player1Influence;
        this.players[1] = player2Influence;
    }

    public void awardInfluence(int player, int quantity) {
        players[player - 1] += quantity;
        int newTotal = players[0] + players[1];
        if (newTotal > totalInfluence){
            players[2 - player] -= newTotal - totalInfluence;
        }
    }

    public int getPlayer1Influence() {
        return players[0];
    }

    public void setPlayer1Influence(int player1) {
        this.players[0] = player1;
    }

    public int getPlayer2Influence() {
        return players[1];
    }

    public void setPlayer2Influence(int player2) {
        this.players[1] = player2;
    }

    public int getTotalInfluence(){
        return totalInfluence;
    }

}

