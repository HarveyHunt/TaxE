package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.GameCore;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 09/01/2015.
 */
public class InfoDisplay extends Group {

    private GameCore game;

    private boolean maximised;

    private Button maximise;
    private Button minimise;

    public InfoDisplay(GameCore game) {
        this.game = game;
        maximised = false;

        minimise = new Button(GuiTextures.MINIMISE_BUTTON) {
            @Override
            public void clicked(GameCore game) {
                maximised = false;
                this.setVisible(false);
                maximise.setVisible(true);
            }
        };
        addActor(minimise);
        minimise.setVisible(false);

        maximise = new Button(GuiTextures.MAXIMISE_BUTTON) {
            @Override
            public void clicked(GameCore game) {
                maximised = true;
                this.setVisible(false);
                minimise.setVisible(true);
            }
        };
        addActor(maximise);

        resize();
    }

    public void resize() {
        minimise.setCoordinate(new Coordinate(268, Gdx.graphics.getHeight() - GuiTextures.INFODISPLAY_TOP_MINIMISED.getHeight() - GuiTextures.INFODISPLAY_BACKGROUND.getHeight() - 45));
        maximise.setCoordinate(new Coordinate(218, Gdx.graphics.getHeight() - GuiTextures.INFODISPLAY_TOP_MAXIMISED.getHeight() - 91));
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
