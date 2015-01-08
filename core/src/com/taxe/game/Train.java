package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vlad on 19/11/2014.
 * Class representing players' trains in the game.
 */
public class Train {

    private final TrainType trainType;
    private Cargo cargo;
    private ArrayDeque<Node> path;         // Train travelling path
    private Coordinate coordinate;          // For drawing and if train is moving

    // variables to handle the moving during the execution phase.
    private boolean moving;
    private ArrayList<Sleeper> sleeperPath;
    private double distance;

    //constants for drawing
    private final int texWidth;
    private final int texHeight;
    private final Texture tokenTexture;

    public Train(TrainType trainType, Node startNode) {
        this.trainType = trainType;
        this.cargo = null;
        this.path = new ArrayDeque<>();
        path.add(startNode);
        this.coordinate = startNode.getCoordinate();

        this.moving = false;
        this.sleeperPath = null;
        this.distance = 0.0;

        this.tokenTexture = new Texture("image.png");
        this.texWidth = tokenTexture.getWidth();
        this.texHeight = tokenTexture.getHeight();
    }

    public void beginTravel() {
        // Do the initial calculations and set up the travel variables
        // ACCESSING SLEEPERS GOES HERE - for now, using temporary sleeperPath
        sleeperPath = new ArrayList<>();
        sleeperPath.add(new Sleeper(new Coordinate(100,100),0));
        sleeperPath.add(new Sleeper(new Coordinate(200,100),0));
        sleeperPath.add(new Sleeper(new Coordinate(300,100),0));

        // Calculate total distance of path to travel (so we don't have to do it every time)
        distance = 0.0;
        for (int i = 1; i < sleeperPath.size(); i++) {
            distance += Coordinate.distanceBetween(
                    sleeperPath.get(i).getCoordinate(),
                    sleeperPath.get(i - 1).getCoordinate());
        }

        moving = true;
    }

    public void travel(double x) {
        // Moves the train to the position that is x percentage along its sleeperPath
        // x is a percentage in decimal form, 0.0 - 1.0
        double xDistance = distance * x; // Distance along the path
        double iDistance = 0.0; // counter
        int i = 0;
        double spacing = 0;
        while (iDistance < xDistance) {
            i++;
            if (i == sleeperPath.size()) {
                return; // Stopping list size errors
            }
            spacing = Coordinate.distanceBetween(
                    sleeperPath.get(i - 1).getCoordinate(),
                    sleeperPath.get(i).getCoordinate());
            iDistance += spacing;
        }
        double overShoot = iDistance - xDistance; // How far past xDistance we went
        double percentage = 1 - overShoot / spacing;
        coordinate = Coordinate.coordinateAlongLine(sleeperPath.get(i - 1).getCoordinate(), sleeperPath.get(i).getCoordinate(), percentage);
    }

    public void endTravel() {
        moving = false;
        sleeperPath = null; // Is this a clean deletion??
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
        Iterator<Node> iterator = path.iterator();
        if (iterator.hasNext()) {
            iterator.next();
            return (iterator.hasNext()) ? iterator.next() : null;
        } else {
            return null;
        }
    }

    public Node popPathNode() {
        return path.removeFirst();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                tokenTexture, (float)coordinate.getX(), (float)coordinate.getY(),
                texWidth / 2, texHeight / 2, texWidth, texHeight, 1.0f, 1.0f,
                0,
                0, 0, texWidth, texHeight, false, false);
    }

}
