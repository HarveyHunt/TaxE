package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Owen on 19/11/2014.
 */
public class Map {

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
        ArrayList<Node> nodes = Node.readNodes(nodesFileName);
        for (Node n : nodes) {
            System.out.println(n);
            if (n instanceof City)
                cities.add((City)n);
            else if (n instanceof Homebase)
                homebases.add((Homebase) n);
            else if (n instanceof Junction)
                junctions.add((Junction) n);
            else if (n instanceof IntermediatePoint)
                intermediatePoints.add((IntermediatePoint) n);
        }
        tracks = Track.readTracks(tracksFileName, nodes);
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

    public void draw(SpriteBatch batch) {
        for (Track t: tracks) {
            t.draw(batch);
        }
        for (City c: cities) {
            c.draw(batch);
        }
        for (Homebase h: homebases) {
            h.draw(batch);
        }
        for (Junction j: junctions) {
            j.draw(batch);
        }
        for (IntermediatePoint i: intermediatePoints) {
            i.draw(batch);
        }
    }

}
