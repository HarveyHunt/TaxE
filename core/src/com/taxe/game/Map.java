package com.taxe.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.Tracks.Track;
import com.taxe.game.nodes.*;

import java.io.IOException;
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
        tracks = new ArrayList<>(Track.readTracks(tracksFileName, nodes));
        for (Track t : tracks) {
            this.addActor(t);
        }
        for (Node n : nodes) {
            this.addActor(n);
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Homebase> getHomebases() {
        return homebases;
    }

    public List<Junction> getJunctions() {
        return junctions;
    }

    public List<IntermediatePoint> getIntermediatePoints() {
        return intermediatePoints;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public List<Track> getTracksWith(Node n) {
        List<Track> nt = new ArrayList<>();
        for (Track t : tracks) {
            if (t.getNodes().contains(n)) {
                nt.add(t);
            }
        }
        return nt;
    }

    public Track getTrackWith(Node n1, Node n2) {
        for (Track t : tracks) {
            if (t.getNodes().contains(n1) && t.getNodes().contains(n2)) {
                return t;
            }
        }
        return null;
    }

}
