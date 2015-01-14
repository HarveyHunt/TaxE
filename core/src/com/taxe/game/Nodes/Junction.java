package com.taxe.game.Nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.Util.Coordinate;
import com.taxe.game.Util.Textures;

/**
 * Junction represents intersection of tracks.
 */
public class Junction extends Node {

    public Junction() {
        super(Textures.JUNCTION);
    }

    public Junction(Coordinate coordinate, String id) {
        super(coordinate, id, Textures.JUNCTION);
    }

    public void setState(int state) {
        this.state = state;
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);
        setBounds(getX() - getOriginX(), getY() - getOriginY(), t.getWidth(), t.getHeight());
        setPosition(getX() + getOriginX(), getY() + getOriginY());
        setTouchable(Touchable.enabled);
    }

}
