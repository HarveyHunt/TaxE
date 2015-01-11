package com.taxe.game.UI;

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

    //For debug drawing
    private ShapeRenderer shapeRenderer;

    public GUI() {

        // the table takes up the whole screen too, but allows us to position widgets on the screen nicely
        table = new Table();
        table.setFillParent(true);
        addActor(table);

        // debug drawing allows us to see what the layout looks like
        shapeRenderer = new ShapeRenderer();

        // Add Widgets to table here
        // Widgets are elements of the
        hud = new HUD();
        addActor(hud);
    }

    public HUD getHUD(){
        return hud;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        table.drawDebug(shapeRenderer); // Draws the debug view of the table layout
        drawChildren(batch, parentAlpha);
    }

}
