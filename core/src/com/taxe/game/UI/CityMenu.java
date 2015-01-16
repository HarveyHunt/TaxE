package com.taxe.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.GameCore;
import com.taxe.game.tasks.Task;
import com.taxe.game.util.Coordinate;

/**
 * Created by Owen on 11/01/2015.
 */
public class CityMenu extends Group {

    private Texture background;
    private Texture influenceBar;
    private Texture influenceBar1;
    private Texture influenceBar2;
    private Texture cargoSlot;

    private Button close;
    private Button plus;
    private Button minus;

    private TextDisplay cityNameText;
    private TextDisplay cargoNameText;
    private TextDisplay cargoAmountText;
    private TextDisplay influencePlayer1Text;
    private TextDisplay inluencePlayer2Text;
    private TextDisplay taskText;
    private TextDisplay rewardText;
    private TextDisplay timeLeftText;

    private CharSequence cityName;
    private CharSequence cargoName;
    private int cargoAmount;
    private int influencePlayer1;
    private int influencePlayer2;
    private Task task;

    public CityMenu() {
        background = new Texture("UI/city menu background.png");
        influenceBar = new Texture("UI/influence bar.png");
        influenceBar1 = new Texture("UI/orange bar.png");
        influenceBar2 = new Texture("UI/blue bar.png");
        cargoSlot = new Texture("UI/cargo slot.png");

        close = new Button(new Texture("UI/x button.png"), new Coordinate()) {
            @Override
            public void clicked(GameCore game) {
                // Close the city menu
            }
        };
        addActor(close);

        plus = new Button(new Texture("UI/+ button.png"), new Coordinate()) {
            @Override
            public void clicked(GameCore game) {
                // cargo ++
            }
        };
        addActor(plus);

        minus = new Button(new Texture("UI/- button.png"), new Coordinate()) {
            @Override
            public void clicked(GameCore game) {
                // cargo --
            }
        };
        addActor(minus);

        //cityName = new TextDisplay()

        resize(); // Resize is run to set the location of the buttons and textDisplays
    }

    public void resize() {
        close.setCoordinate(new Coordinate(Gdx.graphics.getWidth() - 42, Gdx.graphics.getHeight() - 121));
        minus.setCoordinate(new Coordinate(Gdx.graphics.getWidth() - 42, Gdx.graphics.getHeight() - 121));
        plus.setCoordinate(new Coordinate(Gdx.graphics.getWidth() - 42, Gdx.graphics.getHeight() - 121));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        GUI.drawElement(
                batch, background,
                Gdx.graphics.getWidth() - background.getWidth() - 30, Gdx.graphics.getHeight() - background.getHeight() - 110
        );

        drawChildren(batch, parentAlpha);
    }

}
