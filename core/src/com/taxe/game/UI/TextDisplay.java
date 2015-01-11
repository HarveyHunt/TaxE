package com.taxe.game.UI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.taxe.game.Coordinate;

/**
 * Created by Owen on 09/01/2015.
 */
public class TextDisplay extends Actor {

    private CharSequence text;
    private Coordinate coordinate;
    private BitmapFont font;
    private boolean visible;

    public TextDisplay(CharSequence text, Coordinate coordinate) {
        this.text = text;
        this.coordinate = coordinate;
        font = new BitmapFont();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (visible){
            font.draw(batch, text, (float) coordinate.getX(), (float) coordinate.getY());
        }
    }

}
