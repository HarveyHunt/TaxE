package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.Iterator;

public class NotificationBox extends Group {

    private ArrayList<Label> labels;
    private int labelY;
    private static final int MAX_DISPLAYED_LABELS = 5;

    public NotificationBox() {
        labels = new ArrayList<>();
        labelY = Gdx.graphics.getHeight() - 800;
    }

    public void draw(Batch batch, float parentAlpha) {

        batch.draw(
                GuiTextures.NOTIFICATION_BOX,
                30, Gdx.graphics.getHeight() - 800);

        drawChildren(batch, parentAlpha);
    }

    private void removeDeadLabels(){
        if (labels.size() == 0)
            return;

        for (Iterator<Label> iter = labels.iterator(); iter.hasNext();) {
            if (iter.next().getColor().a == 0) {
                iter.remove();
            }
        }
        rearrangeLabels();
    }

    private void rearrangeLabels() {
        int count = 0;
        labelY = Gdx.graphics.getHeight() - 800;

        for (Label l : labels) {
            if (count == MAX_DISPLAYED_LABELS)
                break;
            l.setPosition(50, labelY);
            l.setVisible(true);
            labelY += l.getTextBounds().height + 10;
            count++;
        }
    }

    public void addLabel(Label label, float duration) {
        label.setPosition(50, labelY);
        labelY += label.getTextBounds().height + 10;

        SequenceAction seq = new SequenceAction();
        seq.addAction(Actions.delay(duration));
        seq.addAction(Actions.fadeOut(0.3f));
        seq.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                removeDeadLabels();
                return true;
            }
        });

        label.addAction(seq);

        addActor(label);
        label.setVisible(false);
        labels.add(label);
        rearrangeLabels();
    }
}
