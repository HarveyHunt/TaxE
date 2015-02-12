package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * This Group contains all of the Actors/Groups for the entire game scene. This is basically everything except the GUI.
 * It handles scaling for all of the game elements as one.
 */
public class Scene extends Group {
    /**
     * Scales the entire scene to fit the screen size.
     */
    public void scale() {
        final float SCENE_WIDTH = 1410.0f;
        final float SCENE_HEIGHT = 890.0f;
        float ratio = SCENE_WIDTH / SCENE_HEIGHT;

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight() - 70;
        float scale;
        if (width / height < ratio) {
            // if the screen is proportionally too tall
            scale = width / SCENE_WIDTH;
        } else {
            // if the screen is proportionally too wide or is perfectly proportional
            scale = height / SCENE_HEIGHT;
        }
        // update values
        setScale(scale);
        setPosition(
                Gdx.graphics.getWidth() / 2 - (SCENE_WIDTH * scale) / 2 + getOriginX(),
                Gdx.graphics.getHeight() / 2 - (SCENE_HEIGHT * scale) / 2 - 70 + getOriginY()
        );
    }

}
