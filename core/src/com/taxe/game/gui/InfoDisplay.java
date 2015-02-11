package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.GameCore;
import com.taxe.game.tasks.Task;

import java.util.ArrayList;

/**
 * The menu on the left hand side of the screen that does not display any information yet
 */
public class InfoDisplay extends Group {

    private GameCore game;
    private Button maximiseButton;
    private Button minimiseButton;
    private ArrayList<Label> taskLabels;
    private boolean maximised;
    private int labelY;

    /**
     * creates an instance of InfoDisplay
     * @param game the GameCore instance
     */
    public InfoDisplay(GameCore game) {
        this.game = game;
        maximised = false;
        taskLabels = new ArrayList<>(5);

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

        labelY = Gdx.graphics.getHeight() - GuiTextures.   INFODISPLAY_TOP_MAXIMISED.getHeight() - 121;

        resize();
    }

    /**
     * repositions the elements of the menu based on the size of the screen.
     */
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
            for (Label l : taskLabels)
                l.setVisible(true);

        } else {
            // Draw minimised stuff!
            batch.draw(
                    GuiTextures.INFODISPLAY_TOP_MINIMISED,
                    30, Gdx.graphics.getHeight() - GuiTextures.INFODISPLAY_TOP_MINIMISED.getHeight() - 110
            );
            for (Label l : taskLabels)
                l.setVisible(false);
        }

        drawChildren(batch, parentAlpha);
    }

    public void addTask(Task task) {
        Label label = new Label(task.toString(), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        label.setAlignment(Align.center);
        label.setPosition(50, labelY);
        labelY -= 50;

        addActor(label);
        taskLabels.add(label);
    }
}
