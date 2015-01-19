package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.GameCore;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 08/01/2015.
 */
public class Gui extends Group {

    private GameCore game;
    private Hud hud;
    private InfoDisplay infoDisplay;
    private GameEndMenu gameEndMenu;

    public Gui(GameCore game) {// Add Other Stuffs
        this.game = game;

        hud = new Hud(game);
        addActor(hud);

        infoDisplay = new InfoDisplay(game);
        addActor(infoDisplay);

        createNotification(GuiTextures.PLAYER_1_TURN_START, new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), 2);
    }

    public void resize() {
        hud.resize();
        infoDisplay.resize();
        if (gameEndMenu != null) {
            gameEndMenu.resize();
        }
    }

    public void createGameEndMenu(int winner) {
        gameEndMenu = new GameEndMenu(winner);
        addActor(gameEndMenu);
    }

    public void createNotification(Texture texture, Coordinate coordinate, float duration) {
        Notification notification = new Notification(this, texture, coordinate, duration);
        addActor(notification);
    }

    public GameCore getGame() {
        return game;
    }

    public Hud getHud() {
        return hud;
    }

    public InfoDisplay getInfoDisplay() {
        return infoDisplay;
    }

    /*@Override
    public void draw(Batch batch, float parentAlpha) {
        drawChildren(batch, parentAlpha);
    }*/

}
