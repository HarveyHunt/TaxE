package com.taxe.game.tracks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Json;
import com.taxe.game.nodes.Node;
import com.taxe.game.util.Coordinate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing tracks in the game. Track is a sequence sleepers going from one node to another. The class
 * supports drawing curved tracks.
 */
public class Track extends Group {

    // Constants for drawing
    private static final float DISTANCE_BETWEEN_SLEEPERS = 8f;
    private static final float PRECISION = 0.02f;
    private static final int CURVE_SIZE = 60;

    private final ArrayList<Node> nodes;
    private final ArrayList<Sleeper> sleepers;

    /**
     * Creates a track from a list of nodes. Track is created by adding sleepers between each subsequent pair of nodes.
     *
     * @param nodes list of nodes
     */
    public Track(List<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
        this.sleepers = new ArrayList<>();

        // Add sleepers between each subsequent pair of nodes
        for (int i = 0; i < nodes.size() - 1; i++) {
            float startAngle;

            Coordinate ca = nodes.get(i).getCoordinate();
            Coordinate cb = nodes.get(i + 1).getCoordinate();
            if (i == 0)
                startAngle = Coordinate.angleBetween(ca, cb);
            else
                startAngle = Coordinate.angleBetween(sleepers.get(sleepers.size() - 2).getCoordinate(), ca);

            sleepers.addAll(getCurve(ca, cb, startAngle));
        }

        // Adding sleeper for the very last node
        if (!nodes.isEmpty()) {
            Node n = nodes.get(nodes.size() - 1);
            sleepers.add(new Sleeper(n.getX(), n.getY(), 0, true));
        }

        // Adding sleepers as actors
        for (Sleeper s : sleepers)
            addActor(s);
    }

    /**
     * Creates tracks specified in json-file. The file is essentially 2D-array, with each 1D-entry specifying a track as
     * a sequence of node ids.
     *
     * @param fileName name of file with track description (node ids)
     * @param nodes    list of nodes; a node must exist for every id mentioned in file
     * @return list of tracks
     * @throws IOException      if something went wrong during reading
     * @throws RuntimeException if some of ids mentioned in file weren't in the list of nodes
     */
    public static List<Track> readTracks(String fileName, List<Node> nodes) throws IOException, RuntimeException {
        // Read ids from json-file
        Json json = new Json();
        FileHandle f = Gdx.files.classpath(fileName);
        String[][] trackIds = json.fromJson(String[][].class, f);

        // Create a track for each sequence of ids
        List<Track> tracks = new ArrayList<>();
        for (String[] ids : trackIds) {
            List<Node> t = new ArrayList<>();
            // Check if all track ids specified in json-file exist
            for (String id : ids) {
                Node n = Node.getNodeById(id, nodes);
                if (n != null) t.add(n);
                else throw new RuntimeException("can't construct track with non-existent id = " + id);
            }
            tracks.add(new Track(t));
        }

        return tracks;
    }

    /**
     * Returns a list of sleepers that form a curve between two points based on a starting direction.
     * <p>
     * This explanation should help you understand how the algorithm in getCurve() draws Bezier curves.
     * Consider 3 coordinates, A, B, and C. <ul> <li>Our curve goes from A to B.</li> <li>The curve starts pointing from
     * A to C.</li> <li>The curve ends pointing from C to B.</li> </ul> The Bezier curve uses the lines AC and CB as its
     * two tangent lines. Take a decimal value between 0 and 1, x. This represents a percentage along a line. xLine(A,
     * C) = the coordinate that x distance along the line AC. 0 is start, 1 is end, 0.5 is middle. xLine(xLine(A, C),
     * xLine(C, B)) is a point on the Bezier curve. x being the same value for each function. xLine is represented by
     * the method coordinateAlongLine(A, B, x)
     *
     * @param ca         first point.
     * @param cb         second point.
     * @param startAngle starting direction
     * @return list of sleepers representing curve between two points
     */
    private List<Sleeper> getCurve(Coordinate ca, Coordinate cb, float startAngle) {
        // Finding third coordinate C, based on CURVE_SIZE
        // Ensure that theta is an angle between -Pi and Pi
        float theta = Coordinate.angleBetween(ca, cb) - startAngle;
        if (theta > Math.PI) theta -= 2 * Math.PI;
        if (theta < -Math.PI) theta += 2 * Math.PI;
        Coordinate cc = new Coordinate(
                ca.getX() + CURVE_SIZE * Math.abs(theta) * (float) Math.cos(startAngle),
                ca.getY() + CURVE_SIZE * Math.abs(theta) * (float) Math.sin(startAngle));


        // Create a list of coordinates that plot out the curve but are not evenly spaced
        // The line is divided into 1 / PRECISION number of segments
        List<Coordinate> curve = new ArrayList<>();
        float length = 0;
        Coordinate previous = ca;
        for (float i = 0; i <= 1.0; i += PRECISION) {
            Coordinate c1 = Coordinate.coordinateAlongLine(ca, cc, i);
            Coordinate c2 = Coordinate.coordinateAlongLine(cc, cb, i);
            Coordinate c3 = Coordinate.coordinateAlongLine(c1, c2, i);
            length += Coordinate.distanceBetween(previous, c3);
            curve.add(c3);
            previous = c3;
        }
        length += Coordinate.distanceBetween(previous, cb);

        // Use the coordinates in curve to create angled sleepers that are spaced out evenly
        // Work out the closest value to DISTANCE_BETWEEN_SLEEPERS that spaces evenly
        float spacing = length / (float) Math.round(length / DISTANCE_BETWEEN_SLEEPERS);
        List<Sleeper> sleeperArc = new ArrayList<>();
        sleeperArc.add(new Sleeper(ca.getX(), ca.getY(), (float) Math.toDegrees(startAngle), true));
        float distance = 0;
        for (int i = 0; i < curve.size() - 1; i++) {
            Coordinate c1 = curve.get(i);
            Coordinate c2 = curve.get(i + 1);
            distance += Coordinate.distanceBetween(c1, c2);
            while (distance >= spacing) {
                distance -= spacing;
                float percentage = 1 - distance / Coordinate.distanceBetween(c1, c2);
                float angle = Coordinate.angleBetween(c1, c2);
                Coordinate c = Coordinate.coordinateAlongLine(c1, c2, percentage);
                sleeperArc.add(new Sleeper(c.getX(), c.getY(), (float) Math.toDegrees(angle) + 90, false));
            }
        }
        return sleeperArc;
    }

    /**
     * Returns a list of track nodes
     *
     * @return a list of nodes
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Returns a list of track sleepers
     *
     * @return a list of sleeper
     */
    public List<Sleeper> getSleepers() {
        return sleepers;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawChildren(batch, parentAlpha);
    }

}
