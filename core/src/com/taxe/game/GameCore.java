package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.taxe.Main;

import java.io.IOException;

/**
 * Created by Owen on 08/11/2014.
 */
public class GameCore implements Screen {

    final Main main;
    private Stage stage;
    private Player player1;
    private Player player2;
    private Map map;

    public GameCore(final Main main) {
        this.main = main;
        // Set up the game
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        try {
            map = new Map("nodes.json", "tracks.json");
        }
        catch (IOException e) {
            System.out.println("Something went wrong :(");
        }
        stage.addActor(map);
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
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        stage.getViewport().update(w, h, true);
    }

    @Override
    public void dispose() {
        // Dispose of resources (stage, textures, sounds)
        stage.dispose();
    }

}
