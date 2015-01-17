package com.taxe.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 16/01/2015.
 */
public class Notification extends Actor {

    private GUI parent;
    private Texture texture;

    public Notification(GUI gui, Texture texture, Coordinate coordinate, float duration) {
        this.parent = gui;
        this.texture = texture;
        setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
        setPosition(coordinate.getX(), coordinate.getY());
        setSize(texture.getWidth(), texture.getHeight());
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.fadeOut(duration));
        sequenceAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                dispose();
                return true;
            }
        });
        addAction(sequenceAction);
    }

    public void dispose() {
        // Remove this notification
        parent.removeActor(this);
    }

    public void draw(Batch batch, float parentAlpha) {
        Color colour = batch.getColor();
        batch.setColor(getColor()); // Sets the colour to include the alpha value of the notification
        batch.draw(texture,
                getX() - getOriginX(), getY() - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                1, 1, 0,
                0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
        batch.setColor(colour);
    }

}
