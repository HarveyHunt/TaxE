package com.taxe.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.Coordinate;
import com.taxe.game.GameCore;

/**
 * Created by Owen on 09/01/2015.
 */
public class InfoDisplay extends Group {

    private boolean maximised;
    private Texture background;
    private Texture topMax;
    private Texture topMin;

    private Button maximise;
    private Button minimise;

    public InfoDisplay() {
        maximised = false;

        background = new Texture("UI/info background.png");
        topMax = new Texture("UI/Top maximised.png");
        topMin = new Texture("UI/Top minimised.png");

        minimise = new Button(new Texture("UI/minimise.png"), new Coordinate()) {
            @Override
            public void clicked(GameCore game) {
                maximised = false;
                this.setVisible(false);
                maximise.setVisible(true);
            }
        };
        addActor(minimise);
        minimise.setVisible(false);

        maximise = new Button(new Texture("UI/maximise.png"), new Coordinate()) {
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

    public void resize(){
        minimise.setCoordinate(new Coordinate(268, Gdx.graphics.getHeight() - background.getHeight() - 135 + 18));
        maximise.setCoordinate(new Coordinate(218, Gdx.graphics.getHeight() - topMax.getHeight() - 91));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (maximised) {
            // Draw maximised stuff!
            GUI.drawElement( // Background
                    batch, background,
                    30, Gdx.graphics.getHeight() - background.getHeight() - 135
            );

            GUI.drawElement(
                    batch, topMax,
                    30, Gdx.graphics.getHeight() - topMax.getHeight() - 110
            );

        } else {
            // Draw minimised stuff!
            GUI.drawElement(
                    batch, topMin,
                    30, Gdx.graphics.getHeight() - topMin.getHeight() - 110
            );
        }

        drawChildren(batch, parentAlpha);
    }

}
