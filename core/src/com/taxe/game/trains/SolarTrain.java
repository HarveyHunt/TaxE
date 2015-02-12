package com.taxe.game.trains;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.nodes.Node;

/**
 * Class representing a solar train. Good for the environment and free to run.
 * Not so great if you're the one mining the rare earth minerals required to
 * make solar panels.
 */
public class SolarTrain extends Train {

    public SolarTrain(Node currentNode) {
        super(3, 3, 0, "Solar train", currentNode);
    }

    public Texture getTexture() {
        return TrainTextures.SOLAR_TRAIN[getState()];
    }

    public void adjustActor() {
        Texture t = getTexture();
        setSize(t.getWidth(), t.getHeight());
        setOrigin(getWidth() / 2f, getHeight() / 2f);
        setTouchable(Touchable.enabled);
    }
}