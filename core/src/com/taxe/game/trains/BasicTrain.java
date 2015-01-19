package com.taxe.game.trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.nodes.Node;

/**
 * Class representing a basic train. A very simple train.
 */
public class BasicTrain extends Train {

    public BasicTrain(Node currentNode) {
        super(3, 1, 1, "Basic train", currentNode);
    }

    public Texture getTexture() {
        return TrainTextures.BASIC_TRAIN[getState()];
    }

    public void adjustActor() {
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setTouchable(Touchable.enabled);

    }

}
