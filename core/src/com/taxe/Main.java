package com.taxe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.taxe.game.GameCore;

/*
----------------
Welcome!
----------------
This is a basic game engine. It has a game loop and allows us to handle drawing, audio and user input (Mouse and Keyboard) very easily.

    render(); -- This is the main game loop. It runs automatically every step of the game, depending on fps.
                 It will update all of the game logic and also then draw the screen.
    create(); -- This is the initialise function. It should set up whatever we need when the game/program is opened.

Features that this framework has that I thought sounded useful:
    - Drawing (duh)
    - Audio
    - File access (saving and stuff maybe)
    - A preferences system (remembers users settings)
    - Input handling

 */


public class Main extends Game {
	public SpriteBatch batch;
    GameCore game;
    Menu menu;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

        //menu = new Menu(this);
        game = new GameCore(this);
        setScreen(game);
	}

	@Override
	public void render () {
		super.render(); // This runs the render method for the current screen class. This calls the default render method in the abstract class Game
	}
}
