package com.taxe.game.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.Coordinate;

/**
 * Created by Owen on 11/01/2015.
 */
public class CityMenu extends Group {

    private Texture background;
    private Texture influenceBar;
    private Texture influencePlayer1;
    private Texture influencePlayer2;
    private Texture cargoSlot;

    private Button close;
    private Button plus;
    private Button minus;

    public CityMenu() {
        background = new Texture("city menu background.png");
        influenceBar = new Texture("influence bar.png");
        influencePlayer1 = new Texture("orange bar.png");
        influencePlayer2 = new Texture("blue bar.png");
        cargoSlot = new Texture("cargo slot.png");

        close = new Button(new Texture("x button.png"), new Coordinate()) {
            @Override
            public void clicked() {
                // Close the city menu
            }
        };

        plus = new Button(new Texture("+ button.png"), new Coordinate()) {
            @Override
            public void clicked() {
                // cargo ++
            }
        };

        minus = new Button(new Texture("- button.png"), new Coordinate()) {
            @Override
            public void clicked() {
                // cargo --
            }
        };
    }

    public void draw(Batch batch, float parentAlpha) {

    }

}
