package com.taxe.game.cards;

import com.taxe.game.GameCore;
import com.taxe.game.Scene;

/**
 * Hand is a way of keeping all card handling logic together. The GameCore has
 * a single instance of Hand.
 */
public class Hand {
    public Block block;
    public Boost boost;

    private boolean isBlockActive;
    private boolean isBoostActive;
    private boolean canUseCard;

    public Hand(Scene scene) {
        block = new Block();
        boost = new Boost();

        isBlockActive = false;
        isBoostActive = false;
        canUseCard = true;

        scene.addActor(block);
        scene.addActor(boost);
    }

    public void updateCardLabels(GameCore game) {
        block.qtyLabel.setText(game.getActivePlayer().getBlockQty().toString());
        boost.qtyLabel.setText(game.getActivePlayer().getBoostQty().toString());
    }

    public boolean isBlockCardActive() {
        return isBlockActive;
    }

    public void setBlockCardState(boolean b) {
        isBlockActive = b;
    }

    public boolean isBoostCardActive() {
        return isBoostActive;
    }

    public void setBoostCardState(boolean b) {
        isBoostActive = b;
    }

    public boolean isCardUsable() {
        return canUseCard;
    }

    public void setCardUsability(boolean b) {
        canUseCard = b;
    }
}
