package com.taxe.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A container that stores labels in it. When a label is removed, it fades out
 * and the other labels are rearranged. Only MAX_DISPLAYED_LABELS are shown at
 * once - the rest are hidden.
 *
 * TODO: Stop a label's animation if it isn't visible else it will only appear
 * for a brief time.
 */
public class NotificationBox extends Group {

    private ArrayList<Label> labels;
    private int labelY;
    private static final int MAX_DISPLAYED_LABELS = 5;
    private static final int GAP = 10;
    private static final int LEFT_MARGIN = 50;
    private static final int BOTTOM_MARGIN = 50;

    public NotificationBox() {
        labels = new ArrayList<>();
        labelY = BOTTOM_MARGIN;
    }

    public void draw(Batch batch, float parentAlpha) {
        drawChildren(batch, parentAlpha);
    }

    /**
     * Remove labels from the label list that have an alpha value of 0 -
     * implying that they have been "faded out"
     */
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

    /**
     * Restack labels so that only 5 are being shown at once.
     */
    private void rearrangeLabels() {
        int count = 0;
        labelY = BOTTOM_MARGIN;

        for (Label l : labels) {
            if (count == MAX_DISPLAYED_LABELS)
                break;
            l.setPosition(50, labelY);
            l.setVisible(true);
            labelY += l.getTextBounds().height + 10;
            count++;
        }
    }

    /**
     * Add a label to the NotificationBox that last for duration seconds.
     *
     * Labels are given an animation and are removed once their animation has
     * finished.
     *
     * @param label The label to be added.
     * @param duration How long (in seconds) to display the label for.
     */
    public void addLabel(Label label, float duration) {
        label.setPosition(LEFT_MARGIN, labelY);
        labelY += label.getTextBounds().height + GAP;

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
