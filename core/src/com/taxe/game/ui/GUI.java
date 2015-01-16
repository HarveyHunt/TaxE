package com.taxe.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Created by Owen on 08/01/2015.
 */
public class GUI extends Group {

    private HUD hud;
    private InfoDisplay infoDisplay;
    private CityMenu cityMenu;

    public GUI() {// Add Other Stuffs
        hud = new HUD(50, 50);
        addActor(hud);

        infoDisplay = new InfoDisplay();
        addActor(infoDisplay);
        infoDisplay.setTouchable(Touchable.enabled);

        cityMenu = new CityMenu();
        //addActor(cityMenu);
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
