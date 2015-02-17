package com.taxe.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * The Healthbar is used to represent the players healths on the HUD
 * Vlad makes me work hard. Send help.
 */
public class Healthbar extends Actor {

    private boolean flipped;
    private float percentage;

    /**
     * creates an instance of Healthbar
     *
     * @param flipped whether the drawing of this healthbar should be flipped or not
     */
    public Healthbar(boolean flipped) {
        this.flipped = flipped;
        this.percentage = 1f;
    }

    /**
     * set the percentage value used to draw the healthbar
     *
     * @param percentage 1.0f is max health. 0.0f is min health
     */
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                GuiTextures.RED_HEALTHBAR,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                1, 1, 0,
                0, 0, GuiTextures.RED_HEALTHBAR.getWidth(), GuiTextures.RED_HEALTHBAR.getHeight(),
                flipped, false
        );
        batch.draw(
                GuiTextures.GREEN_HEALTHBAR,
                getX() + (flipped ? getWidth() * (1 - percentage) : 0), getY(),
                getOriginX(), getOriginY(),
                getWidth() * percentage, getHeight(),
                1, 1, 0,
                0, 0, GuiTextures.GREEN_HEALTHBAR.getWidth(), GuiTextures.GREEN_HEALTHBAR.getHeight(),
                flipped, false
        );
    }

}
