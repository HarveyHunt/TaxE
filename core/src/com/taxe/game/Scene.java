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
        // 1920 is the width of the scene
        // 1080 is the height
        float ratio = 1410.0f / 890.0f;

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight() - 70;
        float scale;
        if (width / height < ratio) {
            // if the screen is proportionally too tall
            scale = width / 1410f;
        } else {
            // if the screen is proportionally too wide or is perfectly proportional
            scale = height / 890f;
        }
        // update values
        setScale(scale);
        setPosition(
                Gdx.graphics.getWidth() / 2 - (1410f * scale) / 2 + getOriginX(),
                Gdx.graphics.getHeight() / 2 - (890f * scale) / 2 - 70 + getOriginY()
        );
    }

}
