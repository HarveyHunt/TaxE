package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Vlad on 19/11/2014.
 * Class representing different types of cargo.
 * For each different CargoType, only one instance exists in the game.
 * CargoTypes are specified and read from JSON file.
 */
public class CargoType {

    private final String name;
    private final Texture texture;

    public CargoType(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getName() {
        return name;
    }
}
