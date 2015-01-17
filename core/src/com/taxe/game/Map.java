package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.nodes.*;
import com.taxe.game.tracks.Track;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents map in the game. Map consists of a background, nodes and tracks connecting the nodes.
 * @see com.taxe.game.nodes
 * @see com.taxe.game.tracks
 */
public class Map extends Group {

    private ArrayList<Node> nodes;
    private ArrayList<City> cities;
    private ArrayList<Homebase> homebases;
    private ArrayList<Junction> junctions;
    private ArrayList<IntermediatePoint> intermediatePoints;
    private ArrayList<Track> tracks;
    private Texture texture = new Texture("map/background.png");

    /**
     * Creates map by reading nodes and tracks from json-files
     * @param nodesFileName name of file describing nodes
     * @param tracksFileName name of file describing tracks
     * @throws IOException
     */
    public Map(String nodesFileName, String tracksFileName) throws IOException {
        setBounds(0f, 0f, 1410f, 890f);
        setOrigin(0, 0);
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

    /**
     * Returns a list of nodes on the map.
     * @return list of nodes.
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Returns list of cities on the map.
     * @return list of cities.
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Returns list of homebases on the map.
     * @return list of homebases.
     */
    public List<Homebase> getHomebases() {
        return homebases;
    }

    /**
     * Returns list of junctions on the map.
     * @return list of junctions.
     */
    public List<Junction> getJunctions() {
        return junctions;
    }

    /**
     * Returns list of intermediate points on the map.
     * @return list of intermediate points.
     */
    public List<IntermediatePoint> getIntermediatePoints() {
        return intermediatePoints;
    }

    /**
     * Returns list of tracks on the map.
     * @return list of tracks.
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * Returns list of tracks containing specified node.
     * @param n node
     * @return list of tracks containing n. If there are no tracks containing n, an empty list is returned.
     */
    public List<Track> getTracksWith(Node n) {
        List<Track> nt = new ArrayList<>();
        for (Track t : tracks) {
            if (t.getNodes().contains(n)) {
                nt.add(t);
            }
        }
        return nt;
    }

    /**
     * Returns a track containing both specified nodes; nodes must not be equal.
     * @param n1 first node
     * @param n2 second node
     * @return a track containing n1 and n2. If there's no such track, returns null.
     */
    public Track getTrackWith(Node n1, Node n2) {
        for (Track t : tracks) {
            if (t.getNodes().contains(n1) && t.getNodes().contains(n2)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isTransform()) applyTransform(batch, computeTransform());

        batch.draw(
                texture, getX() - getOriginX(), getY() - getOriginY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                1, 1, 0,
                0, 0, texture.getWidth(), texture.getHeight(),
                false, false);

        drawChildren(batch, parentAlpha);
        if (isTransform()) resetTransform(batch);
    }

}
