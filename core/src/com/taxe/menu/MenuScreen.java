package com.taxe.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.taxe.Main;
import com.taxe.game.gui.Button;

/**
 * The Screen that represents the menu
 */
public class MenuScreen implements Screen {

    private Main main;
    private Stage stage;
    private Menu menu;

    /**
     * The screen for the menu. Allows the user to start the game or exit.
     * The elements of the menu are stored in an instance of the Menu class.
     * @param main
     */
    public MenuScreen(Main main) {
        this.main = main;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        menu = new Menu(main);
        stage.addActor(menu);

        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actor target = event.getTarget();
                if (target instanceof Button) {
                    ((Button) target).clicked();
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(0.937f, 0.980f, 1, 1);
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
        menu.resize();
    }

    @Override
    public void dispose() {

    }

}
