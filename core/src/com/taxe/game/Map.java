package com.taxe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.Nodes.*;

import java.io.IOException;
import java.util.ArrayList;

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
        tracks = Track.readTracks(tracksFileName, nodes);
        for (Track t : tracks) {
            this.addActor(t);
        }
        for (Node n : nodes) {
            this.addActor(n);
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
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
        for (Track t : tracks) {
            if (t.getPath().contains(n)) {
                nt.add(t);
            }
        }
        return (nt.size() == 0) ? null : nt;
    }

    public Track getTrackWith(Node n1, Node n2) {
        for (Track t : tracks) {
            if (t.getPath().contains(n1) && t.getPath().contains(n2)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.flush();

        float ratio = 1410.0f / 890.0f; // Ratio of width/height --- THESE NEED REPLACING WITH TEXTURE.GETWIDTH/HEIGHT()

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight() - 70;
        float scale;
        if (width / height < ratio) { // if the screen is proportionally too tall
            scale = width / 1410.0f;
        } else {
            scale = height / 890.0f;
        }
        applyTransform(batch, new Matrix4(new Vector3((width - 1410.0f * scale) / 2, (height - 890.0f * scale) / 2, 0), new Quaternion(), new Vector3(scale, scale, 0)));

        this.drawChildren(batch, parentAlpha);
    }

}
