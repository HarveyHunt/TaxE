package com.taxe.game.gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;

/**
 * Created by Owen on 17/01/2015.
 */
public class GameEndMenu extends Group {

    private Texture background;
    private Texture winnerTexture;
    private Button toMenuButton;
    private Button exitGameButton;

    public GameEndMenu(int winner) {
        background = GuiTextures.INFODISPLAY_BACKGROUND;
        setOrigin(background.getWidth() / 2, background.getHeight() / 2);
        setSize(background.getWidth(), background.getHeight());

        toMenuButton = new Button(GuiTextures.MAIN_MENU_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Commands.exitToMenuCommand.executeCommand(gameCore, null);
            }
        };
        addActor(toMenuButton);

        exitGameButton = new Button(GuiTextures.EXIT_GAME_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Gdx.app.exit();
            }
        };
        addActor(exitGameButton);

        winnerTexture = (winner == 0) ? GuiTextures.PLAYER_1_WINS : GuiTextures.PLAYER_2_WINS;

        resize();
    }

    public void resize() {
        toMenuButton.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        exitGameButton.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 150);
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                background,
                Gdx.graphics.getWidth() / 2 - getOriginX() * 1.5f, Gdx.graphics.getHeight() / 2 - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth() * 1.5f, getHeight(),
                1, 1, 0,
                0, 0, background.getWidth(), background.getHeight(),
                false, false);

        batch.draw(
                winnerTexture,
                Gdx.graphics.getWidth() / 2 - winnerTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 80,
                0, 0,
                winnerTexture.getWidth(), winnerTexture.getHeight(),
                1, 1, 0,
                0, 0, winnerTexture.getWidth(), winnerTexture.getHeight(),
                false, false);

        drawChildren(batch, parentAlpha);
    }

}
