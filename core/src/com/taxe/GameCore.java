package com.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by Owen on 08/11/2014.
 */
public class GameCore implements Screen {

    final Main main;

    private ExampleObject exampleObject;

    public GameCore(final Main main){
        this.main = main;
    }

    @Override
    public void render(float delta){
        // delta is the elapsed time in milliseconds

        // Main game loop right here. Oh mama.
        exampleObject.update(delta);

        //Clear screen
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Draw all the things!!!
        main.batch.begin();
        // --------- Draw specific items in order of depth ----------- //
        exampleObject.render(delta);
        // ----------------------------------------------------------- //
        main.batch.end();
    }

    @Override
    public void show(){
        // This method is run when the gae screen is created or switched to
        exampleObject = new ExampleObject(this);
    }

    @Override
    public void hide(){

    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void resize(int w, int h){

    }

    @Override
    public void dispose(){
        // Dispose of resources (textures, sounds)
    }

}
