package com.taxe.game.trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.nodes.Node;

/**
 * Class representing a nuclear train. "Good" for the environment and pretty cheap.
 * The French love it and the Russians don't.
 */
public class NuclearTrain extends Train {

    public NuclearTrain(Node currentNode) {
        super(3, 3, 1, "Nuclear train", currentNode);
    }

    public Texture getTexture() {
        return TrainTextures.NUCLEAR_TRAIN[getState()];
    }
}