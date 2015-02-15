package com.taxe.game.cards;

import com.taxe.game.GameCore;
import com.taxe.game.Scene;

import com.taxe.game.cards.*;

/**
 * Created by henry on 15/02/15.
 */
public class Hand {
    public Block block;
    public Boost boost;

    private boolean isBlockActive;

    public Hand(Scene scene) {
        block = new Block();
        boost = new Boost();

        isBlockActive = false;

        scene.addActor(block);
        scene.addActor(boost);
    }

    public void UpdateCardLabels(GameCore game) {
        block.qtyLabel.setText(game.getActivePlayer().getBlockQty().toString());
        boost.qtyLabel.setText(game.getActivePlayer().getBoostQty().toString());
    }

    public boolean isBlockCardActive() { return isBlockActive; }
    public void setBlockCardState(boolean b) { isBlockActive = b; }
}
