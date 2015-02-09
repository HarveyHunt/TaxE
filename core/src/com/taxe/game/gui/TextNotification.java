package com.taxe.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.taxe.game.util.Coordinate;

/**
 * A text notification is an label that is displayed for a specified duration, then fades out and is deleted
 */
public class TextNotification extends Actor {

    private Label label;

    /**
     * creates an instance of TextNotification
     * @param parent the parent group that created this.
     * @param label the Label to be rendered.
     * @param coordinate the center position of the notification
     * @param duration the duration it is displayed for until it fades out
     */
    public TextNotification(Group parent, Label label, Coordinate coordinate, float duration) {
        setParent(parent);
        this.label = label;
        setOrigin(label.getWidth() / 2, label.getHeight() / 2);
        setBounds(coordinate.getX(), coordinate.getY(), label.getWidth(), label.getHeight());
        SequenceAction seq = new SequenceAction();
        seq.addAction(Actions.delay(duration));
        seq.addAction(Actions.fadeOut(0.3f));
        seq.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                getParent().removeActor(TextNotification.this);
                return true;
            }
        });
        addAction(seq);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Sets the colour to include the alpha value of the notification
        Color colour = batch.getColor();
        batch.setColor(getColor());

        label.draw(batch, parentAlpha);

        // Undo the changes to batch so as to not draw everything transparent
        batch.setColor(colour);
    }
}