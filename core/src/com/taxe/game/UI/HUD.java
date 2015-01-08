package com.taxe.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Owen on 08/01/2015.
 */
public class HUD {

    private Stage stage;
    private Table table;

    //For debug drawing
    private ShapeRenderer shapeRenderer;

    public HUD() {
        // stage is the entire screen itself
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // the table takes up the whole screen too, but allows us to position widgets on the screen nicely
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // debug drawing allows us to see what the layout looks like
        shapeRenderer = new ShapeRenderer();

        // Add Widgets to table here
        // Widgets are elements of the HUD
    }

}
