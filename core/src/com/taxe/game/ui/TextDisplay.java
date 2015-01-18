package com.taxe.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 09/01/2015.
 */
public class TextDisplay extends Actor {

    private BitmapFont font;
    private Label label;

    public TextDisplay(CharSequence text, Color colour, float scale) {
        font = new BitmapFont();
        font.setColor(colour);
        font.setScale(scale);

        label = new Label(text, new Label.LabelStyle(font, colour));
        label.setAlignment(Align.center);
    }

    public TextDisplay(CharSequence text) {
        this(text, Color.WHITE, 1f);
    }

    public void setCoordinate(Rectangle rectangle) {
        label.setBounds(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public void setText(CharSequence text) {
        label.setText(text);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            label.draw(batch, parentAlpha);
        }
    }

}
