package com.taxe.game.Nodes;

import com.taxe.game.Util.Coordinate;
import com.taxe.game.Util.Textures;

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
