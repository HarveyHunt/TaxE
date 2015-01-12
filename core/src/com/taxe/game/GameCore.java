package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.taxe.game.Commands.ActivatePlayerCommand;
import com.taxe.game.InputHandling.Clickable;
import com.taxe.game.Nodes.Node;
import com.taxe.game.Resources.Fuel;
import com.taxe.game.Resources.Gold;
import com.taxe.game.Trains.BasicTrain;
import com.taxe.game.Trains.Train;
import com.taxe.game.UI.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Owen on 08/11/2014.
 */
public class GameCore implements Screen {

    private Stage stage;
    private GUI gui;
    private ArrayList<Player> players;
    private int activePlayer;
    private Map map;
    private ArrayList<Node> selectedPath = new ArrayList<>();

    public GameCore() {
        // Set up the game
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        try {
            map = new Map("nodes.json", "tracks.json");
        } catch (IOException e) {
            System.out.println("Something went wrong :(");
        }
        stage.addActor(map);

        // Setting up players and their trains
        Player p1 =  new Player(map.getHomebases().get(0), new ArrayList<Train>(), new Gold(500), new Fuel(10, 0));
        Player p2 = new Player(map.getHomebases().get(1), new ArrayList<Train>(), new Gold(500), new Fuel(10, 0));
        p1.addTrain(new BasicTrain(p1.getHomebase()));
        p2.addTrain(new BasicTrain(p2.getHomebase()));
        players = new ArrayList<>();
        Collections.addAll(players, p1, p2);
        for (Player p: players) {
            stage.addActor(p);
        }
        activePlayer = 0;

        gui = new GUI();
        stage.addActor(gui);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actor target = event.getTarget();
                if (target instanceof Clickable) {
                    ((Clickable) target).clicked(GameCore.this);
                }
            }
        });
        new ActivatePlayerCommand().executeCommand(this, getActivePlayer());
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
        gui.getCityMenu().resize();
    }

    @Override
    public void dispose() {
        // Dispose of resources (stage, textures, sounds)
        stage.dispose();
    }

    public void clearSelectedPath() {
        selectedPath.clear();
    }

    public void addToSelectedPath(Node n) {
        selectedPath.add(n);
    }

    public Node lastInSelectedPath() {
        int s = selectedPath.size();
        return (s == 0) ? null : selectedPath.get(s - 1);
    }

    public void removeLastInSelectedPath() {
        int s = selectedPath.size();
        if (s > 0) selectedPath.remove(s - 1);
    }

    public Map getMap() {
        return map;
    }

    public ArrayList <Player> getPlayers() {
        return players;
    }

    public void switchActivePlayer() {
        activePlayer = (activePlayer  + 1 == players.size()) ? 0 : activePlayer + 1;
    }

    public Player getActivePlayer() {
        return players.get(activePlayer);
    }

    public Player nextActivePlayer() {
        int p = (activePlayer + 1 == players.size()) ? 0 : activePlayer + 1;
        return players.get(p);
    }

}
