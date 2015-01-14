package com.taxe.game.Tracks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.taxe.game.Util.Coordinate;

/**
 * Created by Owen on 28/11/2014.
 */
public class Sleeper extends Actor {

    private Texture texture;
    private boolean ending;

    public Sleeper(Texture texture, boolean ending) {
        this.texture = texture;
        this.ending = ending;
        setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
        setSize(texture.getWidth(), texture.getHeight());
    }

    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    public boolean isEnding() {
        return ending;
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

}
