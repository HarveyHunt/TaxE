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
    private Coordinate coordinate;         // For drawing and if train is moving

    // variables to handle the moving during the execution phase.
    private ArrayList<Coordinate> coordinatePath;

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

        this.coordinatePath = null;

        this.tokenTexture = new Texture("image.png");
        this.texWidth = tokenTexture.getWidth();
        this.texHeight = tokenTexture.getHeight();
    }

    public void beginTravel() {
        // Create a list of sleepers that the train will travel along in this execution phase
        coordinatePath = new ArrayList<>();

        int nodesToMove = 0;
        if (path.size() - 1 >= trainType.getSpeed()) {
            nodesToMove = trainType.getSpeed();
        } else {
            nodesToMove = path.size() - 1;
        }

        if (nodesToMove > 0) {
            Node nodeA = path.pop();
            Node nodeB;
            for (int i = 0; i < nodesToMove - 1; i++) {
                nodeB = path.pop();
                // Search for the Track which contains both nodes (there should be only one if the map data is correct)
                Track track = null;

                while (track == null) {

                }
            }
        }

        coordinatePath.add(new Coordinate(100,100));
    }

    public void endTravel() {
        coordinatePath = null; // Is this a clean deletion??
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
