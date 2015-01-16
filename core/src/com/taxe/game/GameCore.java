package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.taxe.Main;
import com.taxe.game.Commands.ActivatePlayerCommand;
import com.taxe.game.Trains.BasicTrain;
import com.taxe.game.Trains.Train;
import com.taxe.game.UI.GUI;
import com.taxe.game.inputhandling.Clickable;
import com.taxe.game.nodes.Node;
import com.taxe.game.resources.Fuel;
import com.taxe.game.resources.Gold;

import java.io.IOException;
import java.util.*;

/**
 * Created by Owen on 08/11/2014.
 */
public class GameCore implements Screen {

    private Main main;
    private Stage stage;
    private GUI gui;
    private ArrayList<Player> players;
    private int activePlayer;
    private Map map;
    private Scene scene;
    private ArrayDeque<Node> selectedPath = new ArrayDeque<>();

    public GameCore(Main main) {
        this.main = main;

        // Set up the game
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        scene = new Scene();
        stage.addActor(scene);

        try {
            map = new Map("nodes.json", "tracks.json");
        } catch (IOException e) {
            System.out.println("Something went wrong :(");
        }
        scene.addActor(map);

        // Setting up players and their trains
        Player p1 = new Player(map.getHomebases().get(0), new ArrayList<Train>(), new Gold(500), new Fuel(10, 0));
        Player p2 = new Player(map.getHomebases().get(1), new ArrayList<Train>(), new Gold(500), new Fuel(10, 0));
        //p1.addTrain(new BasicTrain(p1.getHomebase()));
        p1.addTrain(new BasicTrain(map.getJunctions().get(0)));
        p2.addTrain(new BasicTrain(p2.getHomebase()));
        players = new ArrayList<>();
        Collections.addAll(players, p1, p2);
        for (Player p: players) {
            scene.addActor(p);
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
        ActivatePlayerCommand.executeCommand(this, getActivePlayer());

        scene.scale();
    }


    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(0.243f, 0.863f, 0.224f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Draw all the things!!!
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
        scene.scale();
    }

    @Override
    public void dispose() {
        // Dispose of resources (stage, textures, sounds)
        stage.dispose();
    }

    public Map getMap() {
        return map;
    }

    public GUI getGui() {
        return gui;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deque<Node> getSelectedPath() {
        return selectedPath;
    }

    public void switchActivePlayer() {
        activePlayer = (activePlayer + 1 == players.size()) ? 0 : activePlayer + 1;
    }

    public Player getActivePlayer() {
        return players.get(activePlayer);
    }

    public Player nextActivePlayer() {
        int p = (activePlayer + 1 == players.size()) ? 0 : activePlayer + 1;
        return players.get(p);
    }

    public boolean isExecutionPhase() {
        for (Train t: getActivePlayer().getTrains())
            if (t.getActions().size > 0)
                return true;
        return false;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

}
