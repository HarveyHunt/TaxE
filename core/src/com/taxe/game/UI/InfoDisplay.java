package com.taxe.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Owen on 09/01/2015.
 */
public class InfoDisplay extends Group {

    private Texture texture;
    private boolean minimised;


    public InfoDisplay() {
        this.texture = new Texture("UI/HUD.png");
        minimised = false;
    }

    public void toggleMinimised() {
        if (minimised) {
            minimised = false;
        } else {
            minimised = true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                texture, 30, Gdx.graphics.getHeight() - 300, // location temporary and can be changed later
                0, 0, texture.getWidth(), texture.getHeight(),
                1, 1, 0,
                0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
        drawChildren(batch, parentAlpha);
    }

}
