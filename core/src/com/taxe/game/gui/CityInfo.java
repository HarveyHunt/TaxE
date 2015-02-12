package com.taxe.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.nodes.City;

public class CityInfo extends Group {

    private City city;
    private Label cityName;
    private Label cityInfluence;

    private final int RIGHT_MARGIN = Gdx.graphics.getWidth()
                                    - GuiTextures.CITY_INFO.getWidth();
    private final int BOTTOM_MARGIN = 10;
    private final int LABEL_GAP = 20;

    public CityInfo() {
        this.city = null;

        setupLabels();

    }

    private void setupLabels() {
        this.cityName = new Label("City name: ",  new Label.LabelStyle(new BitmapFont(),
                Color.WHITE));
        this.cityName.setAlignment(Align.center);
        this.cityName.setPosition(RIGHT_MARGIN, BOTTOM_MARGIN);

        addActor(this.cityName);

        this.cityInfluence = new Label("City influence: ", new Label.LabelStyle(new BitmapFont(),
                Color.WHITE));
        this.cityInfluence.setAlignment(Align.center);
        this.cityInfluence.setPosition(RIGHT_MARGIN, BOTTOM_MARGIN + LABEL_GAP);

        addActor(this.cityInfluence);

    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(GuiTextures.CITY_INFO,
                RIGHT_MARGIN - getOriginX(), BOTTOM_MARGIN - getOriginY(),
                getOriginX(), getOriginY(),
                GuiTextures.CITY_INFO.getWidth(),
                GuiTextures.CITY_INFO.getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0, GuiTextures.CITY_INFO.getWidth(),
                GuiTextures.CITY_INFO.getHeight(),
                false, false);

        drawChildren(batch, parentAlpha);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city, int playerID) {
        this.city = city;

        this.cityName.setText("City name: " + city.getId());
        this.cityInfluence.setText("City influence: " + city.getInfluence(playerID));
    }
}
