package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by vlad on 09/01/15.
 */
public class Textures {

    public static final int ORIGINAL = 0;
    public static final int HIGHLIGHTED = 1;
    public static final int SELECTED = 2;

    public static final Texture[] CITY = {
            new Texture("city.png"),
            new Texture("city-highlighted.png"),
            new Texture("city-selected.png")};

    public static final Texture[] HOMEBASE = {
            new Texture("homebase.png"),
            new Texture("homebase-highlighted.png"),
            new Texture("homebase-selected.png")};

    public static final Texture[] JUNCTION = {
            new Texture("junction.png"),
            new Texture("junction-highlighted.png"),
            new Texture("junction-selected.png")};

    public static final Texture[] INTERMEDIATE = {
            new Texture("intermediate.png"),
            new Texture("intermediate-highlighted.png"),
            new Texture("intermediate-selected.png")};
}
