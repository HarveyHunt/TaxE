package com.taxe.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.taxe.game.util.Coordinate;

/**
 * A notification is an image that is displayed for a specified duration, then fades out and is deleted
 */
public class Notification extends Actor {

    private Texture texture;

    /**
     * creates an instance of Notification
     * @param parent the parent group that created this.
     * @param texture the texture of the notification
     * @param coordinate the center position of the notification
     * @param duration the duration it is displayed for until it fades out
     */
    public Notification(Group parent, Texture texture, Coordinate coordinate, float duration) {
        setParent(parent);
        this.texture = texture;
        setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
        setBounds(coordinate.getX(), coordinate.getY(), texture.getWidth(), texture.getHeight());
        SequenceAction seq = new SequenceAction();
        seq.addAction(Actions.delay(duration));
        seq.addAction(Actions.fadeOut(0.3f));
        seq.addAction(Actions.removeActor());

        addAction(seq);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Sets the colour to include the alpha value of the notification
        Color colour = batch.getColor();
        batch.setColor(getColor());

        batch.draw(
                texture,
                getX() - getOriginX(), getY() - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                1, 1, 0,
                0, 0, texture.getWidth(), texture.getHeight(),
                false, false);

        // Undo the changes to batch so as to not draw everything transparent
        batch.setColor(colour);
    }

}