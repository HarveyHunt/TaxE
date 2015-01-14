package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Owen on 14/01/2015.
 */
public class Scene extends Group{

    public void scale() {
        float ratio = 1410.0f / 890.0f; // Ratio of width/height --- THESE NEED REPLACING WITH TEXTURE.GETWIDTH/HEIGHT()

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight() - 70;
        float scale;
        if (width / height < ratio) { // if the screen is proportionally too tall
            scale = width / 1410f;
        } else {
            scale = height / 890f;
        }
        setScale(scale);
        setPosition(Gdx.graphics.getWidth() / 2 - (1410f * scale) / 2, Gdx.graphics.getHeight() / 2 - (890f * scale) / 2 - 70);
    }

}
