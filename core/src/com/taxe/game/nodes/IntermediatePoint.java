package com.taxe.game.nodes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Intermediate points are nodes located between the end-points of tracks.
 */
public class IntermediatePoint extends Node {

    /**
     * Default constructor. Necessary for {@link #readNodes(String)}
     */
    public IntermediatePoint() {
        super();
    }

    public Texture getTexture() {
        return NodeTextures.INTERMEDIATE[getState()];
    }

    public void adjustActor() {
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setScale(0.75f);
        setTouchable(Touchable.enabled);
    }

    public void validate() throws RuntimeException {
    }

}
