package com.taxe.game.Nodes;

import com.taxe.game.Coordinate;
import com.taxe.game.Textures;

/**
 * Junction represents intersection of tracks.
 */
public class Junction extends Node {

    public Junction() {
        super(Textures.JUNCTION);
    }

    public Junction(Coordinate coordinate, String id) {
        super(coordinate, id, Textures.JUNCTION);
    }

}
