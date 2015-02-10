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
import com.taxe.game.commands.Commands;
import com.taxe.game.inputhandling.Clickable;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.Node;
import com.taxe.game.map.Map;
import com.taxe.game.player.Player;
import com.taxe.game.tasks.Task;
import com.taxe.game.tasks.TaskFactory;
import com.taxe.game.trains.BasicTrain;
import com.taxe.game.trains.Train;
import com.taxe.game.gui.Gui;

import java.io.IOException;
import java.util.*;

/**
 * The game. This is the main class for everything related to the playing of the game itself.
 */
public class GameCore implements Screen {

    private Main main;
    private Stage stage;
    private Gui gui;
    private ArrayList<Player> players;
    private ArrayList<Task> tasks;
    private int activePlayer;
    private Map map;
    private Scene scene;
    private ArrayDeque<Node> selectedPath = new ArrayDeque<>();
    public TaskFactory taskFactory;

    /**
     * creates an instance of GameCore
     * @param main the parent instance of Main
     */
    public GameCore(Main main) {
        this.main = main;
        tasks = new ArrayList<>();

        // Set up the game
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.getBatch().enableBlending();

        scene = new Scene();

        try {
            map = new Map("nodes.json", "tracks.json");
        } catch (IOException e) {
            System.out.println("Can't find nodes.json and/or tracks.json");
            System.exit(1);
        }

        // Setting up players and their trains
        Player p1 = new Player(map.getHomebases().get(0), new ArrayList<Train>(), 500, 0, 10);
        Player p2 = new Player(map.getHomebases().get(1), new ArrayList<Train>(), 500, 0, 10);
        p1.addTrain(new BasicTrain(p1.getHomebase()));
        p2.addTrain(new BasicTrain(p2.getHomebase()));
        players = new ArrayList<>();
        Collections.addAll(players, p1, p2);
        activePlayer = 0;

        this.taskFactory = new TaskFactory(this);

        gui = new Gui(this);

        stage.addActor(scene);
        scene.addActor(map);
        for (Player p : players) {
            scene.addActor(p);
        }
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
        Commands.activatePlayerCommand.executeCommand(this, getActivePlayer());

        scene.scale();
    }


    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(1f, 0.976f, 0.690f, 1);
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
        gui.resize();
        scene.scale();
    }

    @Override
    public void dispose() {
        // Dispose of resources (stage, textures, sounds)
        stage.dispose();
    }

    /**
     * returns the parent instance of main
     * @return instance of main
     */
    public Main getMain() {
        return main;
    }

    /**
     * returns the instance of Map
     * @return map
     */
    public Map getMap() {
        return map;
    }

    /**
     * returns the instance of Gui
     * @return gui
     */
    public Gui getGui() {
        return gui;
    }

    /**
     * return the list of Players
     * @return list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Return the list of tasks
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * returns the path of nodes currently selected
     * @return list of currently selected nodes
     */
    public Deque<Node> getSelectedPath() {
        return selectedPath;
    }

    /**
     * Switches to the next player.
     */
    public void switchActivePlayer() {
        activePlayer = (activePlayer + 1 == players.size()) ? 0 : activePlayer + 1;
    }

    /**
     * returns the currently active Player
     * @return active player
     */
    public Player getActivePlayer() {
        return players.get(activePlayer);
    }

    /**
     * returns the next player to be active
     * @return next active player
     */
    public Player nextActivePlayer() {
        int p = (activePlayer + 1 == players.size()) ? 0 : activePlayer + 1;
        return players.get(p);
    }

    /**
     * returns the Scene
     * @return scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * returns the Stage
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }
}
