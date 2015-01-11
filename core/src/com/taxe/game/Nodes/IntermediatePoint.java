package com.taxe.game.Nodes;

import com.taxe.game.Coordinate;
import com.taxe.game.Textures;

/**
 * Intermediate points are nodes located between the end-points of tracks.
 */
public class IntermediatePoint extends Node {

    public IntermediatePoint() {
        super(Textures.INTERMEDIATE);
    }

    public IntermediatePoint(Coordinate coordinate, String id) {
        super(coordinate, id, Textures.INTERMEDIATE);
    }

}
