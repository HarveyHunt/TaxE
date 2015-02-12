package com.taxe.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.taxe.game.nodes.City;
import com.taxe.game.util.Coordinate;

public class CityInfo extends Group {

    private final Coordinate coord;
    private final City city;
    private final Label cityName;

    public CityInfo(Coordinate coordinate, City city) {
        this.city = city;
        this.coord = coordinate;

        this.cityName = new Label(city.toString(),  new Label.LabelStyle(new BitmapFont(),
                Color.RED));
        this.cityName.setAlignment(Align.center);
        this.cityName.setPosition(this.coord.getX(), this.coord.getY());

        addActor(this.cityName);

        System.out.println("Created cityinfo");
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                GuiTextures.CITY_INFO,
                getX() - getOriginX(), getY() - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                1, 1, 0,
                0, 0, GuiTextures.CITY_INFO.getWidth(),
                GuiTextures.CITY_INFO.getHeight(),
                false, false);
    }

    public City getCity() {
        return city;
    }
}
