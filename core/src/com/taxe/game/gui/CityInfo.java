package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.nodes.City;

import java.util.ArrayList;

/**
 * Stores information about a city, such as Name, activePlayer's level of
 * influence and the cargoes that the city holds.
 */
public class CityInfo extends Group {

    private City city;
    private Label cityName;
    private Label cityInfluence;
    private ArrayList<Button> buttons;

    private final int LEFT_START = Gdx.graphics.getWidth() - 200;
    private final int BOTTOM_MARGIN = 10;
    private final int LABEL_GAP = 20;

    public CityInfo() {
        this.city = null;
        setupLabels();
        buttons = new ArrayList<>();
    }

    /**
     * Create the label objects that will store information about the city.
     */
    private void setupLabels() {
        this.cityName = new Label("",  new Label.LabelStyle(new BitmapFont(),
                Color.WHITE));
        this.cityName.setAlignment(Align.left);
        this.cityName.setPosition(LEFT_START, BOTTOM_MARGIN);
        addActor(this.cityName);

        this.cityInfluence = new Label("", new Label.LabelStyle(new BitmapFont(),
                Color.WHITE));
        this.cityInfluence.setAlignment(Align.left);
        this.cityInfluence.setPosition(LEFT_START, BOTTOM_MARGIN + LABEL_GAP);
        addActor(this.cityInfluence);
    }

    /**
     * Remove the buttons from the scene so that rendering a different city
     * doesn't mean old cargo buttons are displayed.
     */
    private void removeCargoButtons() {
        for (Button b : buttons)
            removeActor(b);
        buttons.clear();
    }

    /**
     * Update labels with the information for city.
     * @param city The city that we are displaying info for.
     * @param playerID The ID of the player who is currently active.
     */
    public void setCity(City city, int playerID) {
        removeCargoButtons();

        this.city = city;
        this.cityName.setText("City name: " + city.getId());
        this.cityInfluence.setText("City influence: " + city.getInfluence(playerID));

        int i = 0;
        for(Cargo c : city.getCargoList()) {
            Button button = new Button(c.getTexture());
            button.setPosition(LEFT_START + (i * button.getWidth()),
                    BOTTOM_MARGIN + (2 * LABEL_GAP));

            addActor(button);
            buttons.add(button);
            i++;
        }
    }
}
