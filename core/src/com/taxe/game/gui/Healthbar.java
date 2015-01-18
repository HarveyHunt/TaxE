package com.taxe.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Owen on 18/01/2015.
 */
public class Healthbar extends Actor {

    private boolean flipped;
    private float percentage;

    public Healthbar(boolean flipped) {
        this.flipped = flipped;
        percentage = 1f;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void resize(Rectangle rectangle) {
        setBounds(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
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
