package com.taxe.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Owen on 09/01/2015.
 */
public class InfoDisplay extends Group {

    private boolean maximised;


    public InfoDisplay() {
        maximised = true;
    }

    public void toggleMaximised() {
        if (maximised) {
            maximised = false;
        } else {
            maximised = true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (maximised) {
            // Draw maximised stuff!
        } else {
            // Draw minimised stuff!
        }

        drawChildren(batch, parentAlpha);
    }

}
