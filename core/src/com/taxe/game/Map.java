package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.Nodes.*;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owen on 19/11/2014.
 */
public class Map extends Group {

    private ArrayList<Node> nodes;
    private ArrayList<City> cities;
    private ArrayList<Homebase> homebases;
    private ArrayList<Junction> junctions;
    private ArrayList<IntermediatePoint> intermediatePoints;
    private ArrayList<Track> tracks;
    private ArrayDeque<Node> trainPath;


    //private Texture texture = new Texture("map-background.png");

    public Map(String nodesFileName, String tracksFileName) throws IOException {
        cities = new ArrayList<>();
        homebases = new ArrayList<>();
        junctions = new ArrayList<>();
        intermediatePoints = new ArrayList<>();
        nodes = new ArrayList<>(Node.readNodes(nodesFileName));
        for (Node n : nodes) {
            if (n instanceof City)
                cities.add((City) n);
            else if (n instanceof Homebase)
                homebases.add((Homebase) n);
            else if (n instanceof Junction)
                junctions.add((Junction) n);
            else if (n instanceof IntermediatePoint)
                intermediatePoints.add((IntermediatePoint) n);
        }
        tracks = Track.readTracks(tracksFileName, nodes);
        trainPath = null;
        for (Track t : tracks) {
            this.addActor(t);
        }
        for (Node n : nodes) {
            this.addActor(n);
        }
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Homebase> getHomebases() {
        return homebases;
    }

    public ArrayList<Junction> getJunctions() {
        return junctions;
    }

    public ArrayList<IntermediatePoint> getIntermediatePoints() {
        return intermediatePoints;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public ArrayList<Track> getTracksWith(Node n) {
        ArrayList<Track> nt = new ArrayList<>();
        for (Track t: tracks) {
            if (t.getPath().contains(n)) {
                nt.add(t);
            }
        }
        return nt;
    }

    public ArrayList<Track> getTracksWith(List<Node> n) {
        ArrayList<Track> nt = new ArrayList<>();
        for (Track t: tracks) {
            if (t.getPath().containsAll(n)) {
                nt.add(t);
            }
        }
        return nt;
    }

    public void changeNeighbourTexture(Node n, int oldTexture, int newTexture) {
        for (Track t : tracks) {
            if (t.getPath().contains(n)) {
                for (Node nt : t.getPath()) {
                    if (nt.getState() == oldTexture) {
                        nt.setState(newTexture);
                    }
                }
            }
        }
    }

    public void changeAllTextures(int oldTexture, int newTexture) {
        for (Track t : tracks) {
            for (Node nt : t.getPath()) {
                if (nt.getState() == oldTexture)
                    nt.setState(newTexture);
            }
        }
    }

    public void changeTrackTexture(int fillTexture, int startTexture, int endTexture) {
        for (Track t : tracks) {
            int t0 = t.getPath().getFirst().getState();
            int t1 = t.getPath().getLast().getState();
            if ((t0 == startTexture && t1 == endTexture) || (t0 == endTexture && t1 == startTexture)) {
                for (Node nt : t.getPath()) {
                    nt.setState(fillTexture);
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawChildren(batch, parentAlpha);
    }

    void handleNodeClick(Node n) {
        if (n.getState() == Textures.ORIGINAL || n instanceof IntermediatePoint) {
            // Do nothing
        } else if (n.getState() == Textures.HIGHLIGHTED) {
            changeAllTextures(Textures.HIGHLIGHTED, Textures.ORIGINAL);
            n.setState(Textures.SELECTED);
            changeTrackTexture(Textures.SELECTED, Textures.SELECTED, Textures.SELECTED);
            changeNeighbourTexture(n, Textures.ORIGINAL, Textures.HIGHLIGHTED);
            trainPath.add(n);
        } else if (n.getState() == Textures.SELECTED) {
            while (trainPath.getLast() != n) {
                Node c = trainPath.getLast();
                changeNeighbourTexture(c, Textures.HIGHLIGHTED, Textures.ORIGINAL);
                changeNeighbourTexture(c, Textures.SELECTED, Textures.ORIGINAL);
                trainPath.removeLast();
            }
            n.setState(Textures.SELECTED);
            changeNeighbourTexture(n, Textures.ORIGINAL, Textures.HIGHLIGHTED);
        }
    }

    public void clearTrainPath() {
        trainPath = null;
    }

    public void initTrainPath(Node n) {
        trainPath = new ArrayDeque<>();
        trainPath.add(n);
    }

    public ArrayDeque<Node> getTrainPath() {
        return trainPath;
    }

}
