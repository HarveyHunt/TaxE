package com.taxe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.taxe.game.GameCore;
import com.taxe.menu.MenuScreen;

public class Main extends Game {

    public SpriteBatch batch;
    private GameCore game;
    private MenuScreen menuScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();

        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }

    public void startGame() {
        game = new GameCore(this);
        setScreen(game);
        menuScreen.dispose();
    }

    public void exitToMenu() {
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
        game.dispose();
    }

    @Override
    public void render() {
        super.render(); // This runs the render method for the current screen class. This calls the default render method in the abstract class Game
    }
}
