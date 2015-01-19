package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.GameCore;

/**
 * Created by Owen on 09/01/2015.
 */
public class InfoDisplay extends Group {

    private GameCore game;
    private Button maximiseButton;
    private Button minimiseButton;
    private boolean maximised;

    public InfoDisplay(GameCore game) {
        this.game = game;
        maximised = false;

        minimiseButton = new Button(GuiTextures.MINIMISE_BUTTON) {
            @Override
            public void clicked(GameCore game) {
                maximised = false;
                this.setVisible(false);
                maximiseButton.setVisible(true);
            }
        };
        addActor(minimiseButton);
        minimiseButton.setVisible(false);

        maximiseButton = new Button(GuiTextures.MAXIMISE_BUTTON) {
            @Override
            public void clicked(GameCore game) {
                maximised = true;
                this.setVisible(false);
                minimiseButton.setVisible(true);
            }
        };
        addActor(maximiseButton);

        resize();
    }

    public void resize() {
        minimiseButton.setPosition(268, Gdx.graphics.getHeight() - GuiTextures.INFODISPLAY_TOP_MINIMISED.getHeight() - GuiTextures.INFODISPLAY_BACKGROUND.getHeight() - 45);
        maximiseButton.setPosition(218, Gdx.graphics.getHeight() - GuiTextures.INFODISPLAY_TOP_MAXIMISED.getHeight() - 91);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (maximised) {
            // Draw maximised stuff!
            batch.draw(
                    GuiTextures.INFODISPLAY_BACKGROUND,
                    30, Gdx.graphics.getHeight() - GuiTextures.INFODISPLAY_BACKGROUND.getHeight() - 135
            );
            batch.draw(
                    GuiTextures.INFODISPLAY_TOP_MAXIMISED,
                    30, Gdx.graphics.getHeight() - GuiTextures.INFODISPLAY_TOP_MAXIMISED.getHeight() - 110
            );
        } else {
            // Draw minimised stuff!
            batch.draw(
                    GuiTextures.INFODISPLAY_TOP_MINIMISED,
                    30, Gdx.graphics.getHeight() - GuiTextures.INFODISPLAY_TOP_MINIMISED.getHeight() - 110
            );
        }

        drawChildren(batch, parentAlpha);
    }

}
