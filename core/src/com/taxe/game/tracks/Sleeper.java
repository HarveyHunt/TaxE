package com.taxe.game.tracks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.taxe.game.util.Coordinate;

/**
 * Base-class for representing sleepers. Different types of sleepers should be implemented by extending this class.
 * Sleepers have a property called "ending". If sleeper.isEnding() is true, that means that the sleeper is at the
 * ending of one of the sub-segments of the track.
 */
public abstract class Sleeper extends Actor {

    private boolean ending;


    /**
     * Creates a sleeper at specified properties.
     * @param x x-coordinate of sleeper
     * @param y y-coordinate of sleeper
     * @param rotation rotation of sleeper, in degrees
     * @param ending does sleeper mark the ending of the segment of a track
     */
    public Sleeper(float x, float y, float rotation, boolean ending) {
        this.ending = ending;
        setBounds(x, y, getTexture().getWidth(), getTexture().getHeight());
        setRotation(rotation);
        setOrigin(getTexture().getWidth() / 2f, getTexture().getHeight() / 2f);
    }

    /**
     * Compares sleeper to another object
     * @param other object to which sleeper is compared
     * @return true if sleeper's coordinate is the same and they both mark an ending of the track
     */
    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof Sleeper))
            return false;
        Sleeper s = (Sleeper) other;
        return (getX() == s.getX() && getY() == s.getY() && ending == s.isEnding());
    }

    /**
     * Returns location of the sleeper.
     * @return location of the sleeper.
     */
    public Coordinate getCoordinate() {
        return new Coordinate(getX(), getY());
    }

    /**
     * Returns if the sleeper is located at the end of one of the segments of the track.
     * @return true if sleeper is at the end of one of the segments of the track.
     */
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
