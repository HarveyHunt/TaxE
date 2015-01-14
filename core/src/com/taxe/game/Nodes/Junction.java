package com.taxe.game.Nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Junction represents intersection of tracks.
 */
public class Junction extends Node {

    public Junction() {
        super();
    }

    public Texture getTexture() {
        return NodeTextures.JUNCTION[getState()];
    }

    public void adjustActor() {
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setTouchable(Touchable.enabled);
    }

}
