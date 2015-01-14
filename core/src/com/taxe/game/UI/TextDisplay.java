package com.taxe.game.UI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.taxe.game.Util.Coordinate;

/**
 * Created by Owen on 09/01/2015.
 */
public class TextDisplay extends Actor {

    private CharSequence text;
    private Coordinate coordinate;
    private BitmapFont font;

    public TextDisplay(CharSequence text, Coordinate coordinate) {
        this.text = text;
        this.coordinate = coordinate;
        font = new BitmapFont();
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            font.draw(batch, text, (float) coordinate.getX(), (float) coordinate.getY());
        }
    }

}
