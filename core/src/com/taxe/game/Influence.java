package com.taxe.game;

/**
 * Created by Owen on 19/11/2014.
 */
public class Influence {
    private int player1Influence;
    private int player2Influence;

    public Influence(int player1Influence, int player2Influence) {
        this.player1Influence = player1Influence;
        this.player2Influence = player2Influence;
    }

    public int getPlayer1Influence() {
        return player1Influence;
    }

    public void setPlayer1Influence(int player1) {
        this.player1Influence = player1;
    }

    public int getPlayer2Influence() {
        return player2Influence;
    }

    public void setPlayer2Influence(int player2) {
        this.player2Influence = player2;
    }

    public int totalInfluence(int player1, int player2){
        return player1 + player2;
    }
    
}

