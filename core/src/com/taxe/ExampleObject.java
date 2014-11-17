package com.taxe;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Owen on 12/11/2014.
 */
public class ExampleObject {

    // This is an example game object

    final GameCore game; // Allows us to access other stuff from this class

    private Texture tex; // The texture representing the object
    // Variables related to the object
    private float x;

    public ExampleObject(final GameCore game){
        this.game = game;
        // Init the object
        tex = new Texture("fish.png");
        x = 100;
    }

    public void update(float delta){
        // Update the object

        x += 100 * (delta);
    }

    public void render(float delta){
        // Draw the object
        game.main.batch.draw(tex, x, 0);
    }

}
