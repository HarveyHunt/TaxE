package com.taxe.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.Main;
import com.taxe.game.gui.Button;

/**
 * A Group that contains all of the elements of the menu screen
 */
public class Menu extends Group {

    private Button startGame;
    private Button exitGame;

    /**
     * creates the Actors of the elements of the menu
     * @param main
     */
    public Menu(final Main main) {
        startGame = new Button(MenuTextures.START_GAME_BUTTON) {
            @Override
            public void clicked() {
                main.startGame();
            }
        };
        addActor(startGame);
        exitGame = new Button(MenuTextures.EXIT_GAME_BUTTON) {
            @Override
            public void clicked() {
                Gdx.app.exit();
            }
        };
        addActor(exitGame);

        resize();
    }

    /**
     * reposition elements based on the size of the screen
     */
    public void resize() {
        startGame.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 150);
        exitGame.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f - 350);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                MenuTextures.START_SCREEN,
                Gdx.graphics.getWidth() / 2 - MenuTextures.START_SCREEN.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 50
        );
        drawChildren(batch, parentAlpha);
    }

}
