package com.taxe.game.trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.nodes.Node;

/**
 * Class representing a coal train. A very simple and boring train that
 * is shit for the environment and kills animals.
 */
public class CoalTrain extends Train {

    public CoalTrain(Node currentNode) {
        super(5, 2, 2, "Coal train", currentNode);
    }

    public Texture getTexture() {
        return TrainTextures.COAL_TRAIN[getState()];
    }
}