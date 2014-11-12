package com.taxe;

import com.badlogic.gdx.Screen;

/**
 * Created by Owen on 08/11/2014.
 */
public class Menu implements Screen {

    final Main main;

    public Menu(final Main main){
        this.main = main;
    }

    @Override
    public void render(float delta){
        // Loopy loop

        // Draw draw draw
        main.batch.begin();
        // DRAW
        main.batch.end();
    }

    @Override
    public void show(){

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

    }

}
