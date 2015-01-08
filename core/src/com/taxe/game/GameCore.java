package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.taxe.Main;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Owen on 08/11/2014.
 */
public class GameCore implements Screen {

    final Main main;
    private Player player1;
    private Player player2;
    private Map map;

    public GameCore(final Main main) {
        this.main = main;
        // Set up the game
        try {
            //Node.writeNode(nodes, "nodes.json");
//            nodes = Node.readNodes("nodes.json");
//            track = new Track(nodes);
            map = new Map("nodes.json", "tracks.json");
//            tracks = Track.readTracks("tracks.json", nodes);
//            for (Node n: nodes) {
//                System.out.println(n);
//            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong :(");
        }
    }

    @Override
    public void render(float delta) {
        // delta is the elapsed time in milliseconds
        handleInput();
        update(delta);
        draw();
    }

    private void handleInput() {

    }

    private void update(float delta) {
//        
    }

    private void draw() {
        //Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Draw all the things!!!
        main.batch.begin();
        // --------- Draw specific items in order of depth ----------- //
//        for (Track t: tracks) {
//            t.draw(main.batch);
////        train.draw(main.batch);
//        }
//        for (Node n: nodes) {
//            n.draw(main.batch);
//        }
        map.draw(main.batch);

        // ----------------------------------------------------------- //
        main.batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int w, int h) {

    }

    @Override
    public void dispose() {
        // Dispose of resources (textures, sounds)
    }

}
