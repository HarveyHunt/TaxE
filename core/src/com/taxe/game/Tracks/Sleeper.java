package com.taxe.game.Tracks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.taxe.game.Util.Coordinate;

/**
 * Created by Owen on 28/11/2014.
 */
public abstract class Sleeper extends Actor {

    private boolean ending;

    public Sleeper(boolean ending) {
        this.ending = ending;
        setOrigin(getTexture().getWidth() / 2f, getTexture().getHeight() / 2f);
        setSize(getTexture().getWidth() / 2f, getTexture().getHeight() / 2f);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Sleeper))
            return false;
        Sleeper s = (Sleeper) other;
        return (getX() == s.getX() && getY() == s.getY() && ending == s.isEnding());
    }

    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    public boolean isEnding() {
        return ending;
    }

    public abstract Texture getTexture();

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(getTexture(),
                getX() - getOriginX(), getY() - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0, getTexture().getWidth(), getTexture().getHeight(), false, false);
    }

}
