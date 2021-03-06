package com.taxe.game.trains;

import com.badlogic.gdx.graphics.Texture;
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
}
