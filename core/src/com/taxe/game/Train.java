package com.taxe.game;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Created by Vlad on 19/11/2014.
 * Class representing players' trains in the game.
 */
public class Train {

    private final TrainType trainType;
    private Cargo cargo;
    private ArrayDeque <Node> path;         // Train travelling path
    private Coordinate coordinate;          // For drawing and if train is not moving

    public Train(TrainType trainType, Coordinate coordinate) {
        this.trainType = trainType;
        this.cargo = null;
        this.path = null;
        this.coordinate = coordinate;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void createPath(ArrayDeque<Node> path) {
        this.path = path;
    }

    public Node currentPathNode() {
        return path.getFirst();
    }

    public Node nextPathNode() {
        Iterator <Node> iterator = path.iterator();
        if (iterator.hasNext()) {
            iterator.next();
            return (iterator.hasNext()) ? iterator.next() : null;
        }
        else {
            return null;
        }

    }

    public Node popPathNode() {
        return path.removeFirst();
    }

}
