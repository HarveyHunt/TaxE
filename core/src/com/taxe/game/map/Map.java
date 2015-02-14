package com.taxe.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.taxe.game.cargo.*;
import com.taxe.game.nodes.City;
import com.taxe.game.nodes.Homebase;
import com.taxe.game.nodes.IntermediatePoint;
import com.taxe.game.nodes.Node;
import com.taxe.game.tracks.Track;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents map in the game. Map consists of a background, nodes and tracks connecting the nodes.
 *
 * @see com.taxe.game.nodes
 * @see com.taxe.game.tracks
 */
public class Map extends Group {

    private ArrayList<Node> nodes;
    private ArrayList<City> cities;
    private ArrayList<Homebase> homebases;
    private ArrayList<IntermediatePoint> intermediatePoints;
    private ArrayList<Track> tracks;
    private Texture texture = new Texture("map/background.png");

    /**
     * Creates map by reading nodes and tracks from json-files
     *
     * @param nodesFileName  name of file describing nodes
     * @param tracksFileName name of file describing tracks
     * @throws IOException
     */
    public Map(String nodesFileName, String tracksFileName) throws IOException {
        // Read and distribute nodes among categories
        cities = new ArrayList<>();
        homebases = new ArrayList<>();
        intermediatePoints = new ArrayList<>();
        nodes = new ArrayList<>(Node.readNodes(nodesFileName));
        for (Node n : nodes) {
            if (n instanceof City)
                cities.add((City) n);
            else if (n instanceof Homebase)
                homebases.add((Homebase) n);
            else if (n instanceof IntermediatePoint)
                intermediatePoints.add((IntermediatePoint) n);
        }

        // Read tracks
        tracks = new ArrayList<>(Track.readTracks(tracksFileName, nodes));

        // Set up map map as a group of nodes and tracks
        for (Track t : tracks)
            this.addActor(t);
        for (Node n : nodes)
            this.addActor(n);
        setBounds(0f, 0f, 1410f, 890f);
        setOrigin(0, 0);

        // Assign cargo to cities.
        ArrayList<Cargo> cargo = new ArrayList<>();

        // TODO: Make these values more interesting...
        cargo.add(new Penguins(100));
        cargo.add(new Wheat(100));
        cargo.add(new Bears(100));
        cargo.add(new Coal(100));

        Random rgen = new Random();

        for (City c : cities) {
            Collections.shuffle(cargo);
            c.setCargoList(cargo.subList(0, rgen.nextInt(cargo.size()) + 1));
        }
    }

    /**
     * Returns a list of nodes on the map.
     *
     * @return list of nodes.
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Returns list of cities on the map.
     *
     * @return list of cities.
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Returns list of homebases on the map.
     *
     * @return list of homebases.
     */
    public List<Homebase> getHomebases() {
        return homebases;
    }

    /**
     * Returns list of intermediate points on the map.
     *
     * @return list of intermediate points.
     */
    public List<IntermediatePoint> getIntermediatePoints() {
        return intermediatePoints;
    }

    /**
     * Returns list of tracks on the map.
     *
     * @return list of tracks.
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * Returns list of tracks containing specified node.
     *
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
     * Returns a track containing both specified nodes
     *
     * @param n1 first node
     * @param n2 second node
     * @return a track containing n1 and n2, null if there is no such track. If n1 == n2, returns one of the tracks
     * containing the node or null if there's no such track.
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
