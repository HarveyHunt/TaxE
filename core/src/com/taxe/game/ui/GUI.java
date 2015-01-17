package com.taxe.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.taxe.game.GameCore;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 08/01/2015.
 */
public class GUI extends Group {

    private GameCore game;
    private HUD hud;
    private InfoDisplay infoDisplay;
    private CityMenu cityMenu;

    public GUI(GameCore game) {// Add Other Stuffs
        this.game = game;

        hud = new HUD(game);
        addActor(hud);

        infoDisplay = new InfoDisplay();
        addActor(infoDisplay);
        infoDisplay.setTouchable(Touchable.enabled);

        cityMenu = new CityMenu();
        //addActor(cityMenu);
    }

    public void newNotification(Texture texture, Coordinate coordinate, float duration) {
        Notification notification = new Notification(this, texture, coordinate, duration);
        addActor(notification);
    }

    public GameCore getGame() {
        return game;
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

    public HUD getHUD() {
        return hud;
    }

    public InfoDisplay getInfoDisplay() {
        return infoDisplay;
    }

    public CityMenu getCityMenu() {
        return cityMenu;
    }

    /*@Override
    public void draw(Batch batch, float parentAlpha) {
        drawChildren(batch, parentAlpha);
    }*/

}
