package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.GameCore;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 08/01/2015.
 */
public class GUI extends Group {

    private GameCore game;
    private HUD hud;
    private InfoDisplay infoDisplay;
    private GameEndMenu gameEndMenu;

    public GUI(GameCore game) {// Add Other Stuffs
        this.game = game;

        hud = new HUD(game);
        addActor(hud);

        infoDisplay = new InfoDisplay(game);
        addActor(infoDisplay);

        newNotification(GuiTextures.PLAYER_1_TURN_START, new Coordinate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), 2);
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

    public static void drawElement(Batch batch, Texture tex, float x, float y) {
        drawElement(batch, tex, x, y, tex.getWidth(), tex.getHeight(), 0, false, false);
    }

    public static void drawElement(Batch batch, Texture tex, float x, float y, float w, float h) {
        drawElement(batch, tex, x, y, w, h, 0, false, false);
    }

    public static void drawElement(Batch batch, Texture tex, float x, float y, float w, float h, boolean flipX, boolean flipY) {
        drawElement(batch, tex, x, y, w, h, 0, flipX, flipY);
    }

    public static void drawElement(Batch batch, Texture tex, float x, float y, float w, float h, float rotation, boolean flipX, boolean flipY) {
        batch.draw( // Draw main hud background
                tex, x, y,
                0, 0, w, h,
                1, 1, rotation,
                0, 0, tex.getWidth(), tex.getHeight(),
                flipX, flipY);
    }

    public void newNotification(Texture texture, Coordinate coordinate, float duration) {
        Notification notification = new Notification(this, texture, coordinate, duration);
        addActor(notification);
    }

    public GameCore getGame() {
        return game;
    }

    public HUD getHUD() {
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
