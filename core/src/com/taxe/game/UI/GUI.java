package com.taxe.game.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Owen on 08/01/2015.
 */
public class GUI extends Group {

    private Table table;
    private HUD hud;
    private InfoDisplay infoDisplay;
    private CityMenu cityMenu;

    //For debug drawing
    private ShapeRenderer shapeRenderer;

    public GUI() {

        // the table takes up the whole screen too, but allows us to position widgets on the screen nicely
        table = new Table();
        table.setFillParent(true);
        addActor(table);

        // debug drawing allows us to see what the layout looks like
        shapeRenderer = new ShapeRenderer();

        // Add Other Stuffs
        hud = new HUD(50, 50);
        addActor(hud);

        infoDisplay = new InfoDisplay();
        addActor(infoDisplay);

        cityMenu = new CityMenu();
        addActor(cityMenu);
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        table.drawDebug(shapeRenderer); // Draws the debug view of the table layout
        drawChildren(batch, parentAlpha);
    }

}
