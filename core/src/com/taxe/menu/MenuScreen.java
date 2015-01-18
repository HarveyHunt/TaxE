package com.taxe.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.taxe.Main;
import com.taxe.game.gui.Button;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 08/11/2014.
 */
public class MenuScreen implements Screen {

    private Main main;
    private Stage stage;
    private Button startGame;

    public MenuScreen(Main main) {
        this.main = main;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        startGame = new Button(new Texture("Gui/end-turn-buttton.png")) {
            @Override
            public void clicked() {
                main.startGame();
            }
        };
        stage.addActor(startGame);

        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Actor target = event.getTarget();
                if (target instanceof Button) {
                    ((Button) target).clicked();
                }
            }
        });

        resize();
    }

    public void resize() {
        startGame.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f));
    }

    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
        resize();
    }

    @Override
    public void dispose() {

    }

}
