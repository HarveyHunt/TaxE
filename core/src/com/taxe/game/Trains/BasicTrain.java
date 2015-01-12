package com.taxe.game.Trains;

import com.taxe.game.Nodes.Node;
import com.taxe.game.Player;
import com.taxe.game.Textures;

/**
 * Created by vlad on 10/01/15.
 */
public class BasicTrain extends Train {

    public BasicTrain(Node currentNode) {
        super(1, 1, 1, "Basic Trains", currentNode, Textures.BASIC_TRAIN);
    }

}
