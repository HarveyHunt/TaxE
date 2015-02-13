package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.cargo.Cargo;
import com.taxe.game.nodes.City;

public class CityInfo extends Group {

    private City city;
    private Label cityName;
    private Label cityInfluence;

    private final int RIGHT_MARGIN = Gdx.graphics.getWidth() - 200;
    private final int BOTTOM_MARGIN = 10;
    private final int LABEL_GAP = 20;

    public CityInfo() {
        this.city = null;
        setupLabels();
    }

    /**
     * Create the label objects that will store information about the city.
     */
    private void setupLabels() {
        this.cityName = new Label("",  new Label.LabelStyle(new BitmapFont(),
                Color.WHITE));
        this.cityName.setAlignment(Align.left);
        this.cityName.setPosition(RIGHT_MARGIN, BOTTOM_MARGIN);
        addActor(this.cityName);

        this.cityInfluence = new Label("", new Label.LabelStyle(new BitmapFont(),
                Color.WHITE));
        this.cityInfluence.setAlignment(Align.left);
        this.cityInfluence.setPosition(RIGHT_MARGIN, BOTTOM_MARGIN + LABEL_GAP);
        addActor(this.cityInfluence);
    }

    /**
     * Update labels with the information for city.
     * @param city The city that we are displaying info for.
     * @param playerID The ID of the player who is currently active.
     */
    public void setCity(City city, int playerID) {
        this.city = city;

        this.cityName.setText("City name: " + city.getId());
        this.cityInfluence.setText("City influence: " + city.getInfluence(playerID));

        for(Cargo c : city.getCargoList()) {
            // TODO: Correctly align this
            c.setPosition(100, 100);
            addActor(c);
        }
    }
}
