package com.taxe.game.Nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.Util.Coordinate;
import com.taxe.game.Util.Textures;

/**
 * Intermediate points are nodes located between the end-points of tracks.
 */
public class IntermediatePoint extends Node {

    public IntermediatePoint() {
        super(Textures.INTERMEDIATE);
    }

    public IntermediatePoint(Coordinate coordinate, String id) {
        super(coordinate, id, Textures.INTERMEDIATE);
    }

    public void setState(int state) {
        this.state = state;
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setBounds(getX() - getOriginX(), getY() - getOriginY(), t.getWidth(), t.getHeight());
        setPosition(getX() + getOriginX(), getY() + getOriginY());
        setTouchable(Touchable.enabled);
    }

}
