package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Resources.Fuel;
import com.taxe.game.Resources.Gold;
import com.taxe.game.Trains.BasicTrain;
import com.taxe.game.Trains.Train;
import com.taxe.game.UI.Button;
import com.taxe.game.UI.GUI;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Owen on 08/11/2014.
 */
public class GameCore implements Screen {

    private Stage stage;
    private GUI gui;
    private Player player1;
    private Player player2;
    private Map map;

    public GameCore() {
        // Set up the game
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        try {
            map = new Map("nodes.json", "tracks.json");
        }
        catch (IOException e) {
            System.out.println("Something went wrong :(");
        }
        stage.addActor(map);
        stage.addListener(new PathClickListener());
        stage.addListener(new PathInputListener());

        player1 = new Player(map.getHomebases().get(0), new ArrayList<Train>(), new Gold(500), new Fuel(10, 0));
        player2 = new Player(map.getHomebases().get(1), new ArrayList<Train>(), new Gold(500), new Fuel(10, 0));
        player1.addTrain(new BasicTrain(player1.getHomebase(), player1));
        player2.addTrain(new BasicTrain(player2.getHomebase(), player2));

        stage.addActor(player1);
        stage.addActor(player2);

        gui = new GUI();
        stage.addActor(gui);
        stage.addListener(new GuiClickListener());
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
        gui.getHUD().resize();
        gui.getInfoDisplay().resize();
    }

    @Override
    public void dispose() {
        // Dispose of resources (stage, textures, sounds)
        stage.dispose();
    }

    private class GuiClickListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            Actor target = event.getTarget();
            if (target instanceof Button) {
                ((Button) target).executeCommand(GameCore.this);
            }
        }
    }

    private class PathClickListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            Actor target = event.getTarget();
            if (target instanceof Node) {
                map.handleNodeClick((Node) target);
            }
            if (target instanceof Train) {
                Train t = (Train) target;
                t.processTrainClick(map);
            }
        }
    }

    private class PathInputListener extends InputListener {
        @Override
        public boolean keyTyped(InputEvent event, char c) {
            if ((int) c == 13) {
                Train th = null;
                for (Train t : player1.getTrains()) {
                    if (t.getCurrentTexture() == Textures.SELECTED)
                        th = t;
                }
                for (Train t : player2.getTrains()) {
                    if (t.getCurrentTexture() == Textures.SELECTED)
                        th = t;
                }
                //th.setPath(map.getTrainPath());
            }
            return true;
        }
    }

}
