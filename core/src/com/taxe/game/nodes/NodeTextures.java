package com.taxe.game.nodes;

import com.badlogic.gdx.graphics.Texture;

/**
 * Specifies textures of all different node types.
 */
public abstract class NodeTextures {

    public static final Texture[] CITY = {
            new Texture("nodes/city.png"),
            new Texture("nodes/city-highlighted.png"),
            new Texture("nodes/city-selected.png")};

    public static final Texture[] HOMEBASE = {
            new Texture("nodes/homebase.png"),
            new Texture("nodes/homebase-highlighted.png"),
            new Texture("nodes/homebase-selected.png")};

    public static final Texture[] INTERMEDIATE = {
            new Texture("nodes/intermediate.png"),
            new Texture("nodes/intermediate-highlighted.png"),
            new Texture("nodes/intermediate-selected.png")};
}
