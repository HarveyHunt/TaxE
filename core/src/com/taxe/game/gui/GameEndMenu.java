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

        toMenu = new Button(GuiTextures.END_TURN_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Commands.exitToMenuCommand.executeCommand(gameCore, null);
            }
        };
        addActor(toMenu);

        exit = new Button(GuiTextures.END_TURN_BUTTON) {
            @Override
            public void clicked(GameCore gameCore) {
                Gdx.app.exit();
            }
        };
        addActor(exit);

        playerTexture = (winner == 0) ? GuiTextures.PLAYER_1_TURN_START : GuiTextures.PLAYER_2_TURN_START;

        resize();
    }

    public void resize() {
        toMenu.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
        exit.setCoordinate(new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 100));
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                texture,
                Gdx.graphics.getWidth() / 2 - getOriginX(), Gdx.graphics.getHeight() / 2 - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                1, 1, 0,
                0, 0, texture.getWidth(), texture.getHeight(),
                false, false);

        batch.draw(
                playerTexture,
                Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 200,
                0, 0,
                playerTexture.getWidth(), playerTexture.getHeight(),
                0.2f, 0.2f, 0,
                0, 0, (int) (playerTexture.getWidth() * 0.6f), playerTexture.getHeight(),
                false, false);

        drawChildren(batch, parentAlpha);
    }

}
