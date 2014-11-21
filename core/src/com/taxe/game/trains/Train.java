package com.taxe.game.trains;

import com.taxe.game.Cargo;
import com.taxe.game.Coordinate;

/**
 * Created by Vladimir on 19/11/2014.
 */
public class Train {

    private TrainType trainType;
    private Cargo cargo;
    private Coordinate position;

    public Train(Cargo cargo, TrainType trainType, Coordinate position) {
        this.cargo = cargo;
        this.trainType = trainType;
        this.position = position;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }

}
