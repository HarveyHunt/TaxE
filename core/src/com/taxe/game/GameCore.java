package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.taxe.Main;
import java.util.ArrayList;

/**
 * Created by Owen on 08/11/2014.
 */
public class GameCore implements Screen {

    final Main main;
    private Player player1;
    private Player player2;
    private Map map;

    private Track track; //THIS SHOULD BE IN MAP, NOT GAMECORE

    private ExampleObject exampleObject;

    public GameCore(final Main main) {
        this.main = main;

        // Set up the game
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(new Coordinate(137, 120), true));
        nodes.add(new Node(new Coordinate(294, 120), true));
        nodes.add(new Node(new Coordinate(397, 222), true));
        nodes.add(new Node(new Coordinate(533, 248), true));
        nodes.add(new Node(new Coordinate(600, 141), true));
        track = new Track(nodes);
    }

    @Override
    public void render(float delta){
        // delta is the elapsed time in milliseconds
        handleInput();
        update(delta);
        draw();

    }

    private void handleInput(){

    }

    private void update(float delta){

    }

    private void draw(){
        //Clear screen
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Draw all the things!!!
        main.batch.begin();
        // --------- Draw specific items in order of depth ----------- //
        exampleObject.draw();
        // ----------------------------------------------------------- //
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
        // Dispose of resources (textures, sounds)
    }

}
