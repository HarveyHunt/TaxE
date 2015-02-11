package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

public class NotificationBox extends Group {

    private ArrayList<Label> labels;

    public void NoficationBox() {
        labels = new ArrayList<>();
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                GuiTextures.NOTIFICATION_BOX,
                30, Gdx.graphics.getHeight() - 1000);
    }

    public void addLabel(Label label) {
        //TODO: Add the label to our internal arraylist and give it an animation.
        // Also, add the label to the scene.
    }
}
