package com.taxe.game.gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.GameCore;
import com.taxe.game.commands.Commands;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 17/01/2015.
 */
public class GameEndMenu extends Group {

    private Texture texture;
    private Texture playerTexture;
    private Button toMenu;
    private Button exit;

    public GameEndMenu(int winner) {
        texture = GuiTextures.INFODISPLAY_BACKGROUND;
        setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
        setSize(texture.getWidth(), texture.getHeight());

        toMenu = new Button(GuiTextures.MAIN_MENU_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Commands.exitToMenuCommand.executeCommand(gameCore, null);
            }
        };
        addActor(toMenu);

        exit = new Button(GuiTextures.EXIT_GAME_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Gdx.app.exit();
            }
        };
        addActor(exit);

        playerTexture = (winner == 0) ? GuiTextures.PLAYER_1_WINS : GuiTextures.PLAYER_2_WINS;

        resize();
    }

    public void resize() {
        toMenu.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
        exit.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 150));
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                texture,
                Gdx.graphics.getWidth() / 2 - getOriginX() * 1.5f, Gdx.graphics.getHeight() / 2 - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth() * 1.5f, getHeight(),
                1, 1, 0,
                0, 0, texture.getWidth(), texture.getHeight(),
                false, false);

        batch.draw(
                playerTexture,
                Gdx.graphics.getWidth() / 2 - playerTexture.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 80,
                0, 0,
                playerTexture.getWidth(), playerTexture.getHeight(),
                1, 1, 0,
                0, 0, playerTexture.getWidth(), playerTexture.getHeight(),
                false, false);

        drawChildren(batch, parentAlpha);
    }

}
