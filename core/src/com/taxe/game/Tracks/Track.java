package com.taxe.game.Tracks;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Json;
import com.taxe.game.nodes.Node;
import com.taxe.game.util.Coordinate;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owen on 18/11/2014.
 */
public class Track extends Actor {

    // Constants for drawing
    private static final float DISTANCE_BETWEEN_SLEEPERS = 8.0f;
    private static final float PRECISION = 0.02f;
    private static final int CURVE_SIZE = 50;

    private final ArrayList<Node> nodes;
    private final ArrayList<Sleeper> sleepers;

    public Track(List<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
        this.sleepers = new ArrayList<>();
        for (int i = 0; i < nodes.size() - 1; i++) {
            Coordinate cA = nodes.get(i).getCoordinate();
            Coordinate cB = nodes.get(i + 1).getCoordinate();
            float startAngle = (i == 0) ?
                    Coordinate.angleBetween(cA, cB) :
                    Coordinate.angleBetween(sleepers.get(sleepers.size() - 2).getCoordinate(), cA);
            sleepers.addAll(getArc(cA, cB, startAngle));
        }
        if (!nodes.isEmpty()) {
            Node n = nodes.get(nodes.size() - 1);
            Sleeper s = new BasicSleeper(true);
            s.setPosition(n.getX(), n.getY());
            sleepers.add(s);
        }
    }

    /*
    ---------------------------------
    getArc() takes a start point, end point, and initial angle and returns a list of coordinates that follow
         the arc between the two points, spaced out by the given interval (in pixels)
    ---------------------------------
    *** The Maths! ***
    Okay, so this is is a cool bit of maths that calculates a Bezier curve. Give Bezier a google to see some pictures.
    This explanation should help you understand the algorithm in getArc()

    Consider 3 coordinates, A, B, and C.
        - Our curve goes from A to B.
        - The curve starts pointing from A to C.
        - The curve ends pointing from C to B.

    The Bezier curve uses the lines AC and CB as its 2 tangent lines.
    Take a decimal value between 0 and 1, x. This represents a percentage along a line.
        xLine(A, C) = the coordinate that x distance along the line AC. 0 is start, 1 is end, 0.5 is middle.
        xLine(xLine(A, C), xLine(C, B)) is a point on the Bezier curve. x being the same value for each function.
        xLine is represented by the method coordinateAlongLine(A, B, x)
    ---------------------------------
     */

    public static List<Track> readTracks(String fileName, ArrayList<Node> nodes) throws IOException {
        Json json = new Json();
        FileReader f = new FileReader(fileName);
        String[][] trackIds = json.fromJson(String[][].class, f);
        f.close();

        ArrayList<Track> tracks = new ArrayList<>();
        for (String[] ids : trackIds) {
            ArrayList<Node> t = new ArrayList<>();
            for (String id : ids) {
                Node n = Node.getNodeById(id, nodes);
                if (n != null) {
                    t.add(n);
                } else {
                    throw new RuntimeException("Can't construct track with id = " + id);
                }
            }
            tracks.add(new Track(t));
        }
        return tracks;


    }

    // Returns a list of sleepers that form a curve between 2 nodes based on a starting direction
    private List<Sleeper> getArc(Coordinate cA, Coordinate cB, float startAngle) {

        // Create a list of coordinates that plot out the arc but are not evenly spaced
        // The line is divided into 1 / precision number of segments
        float theta = Coordinate.angleBetween(cA, cB) - startAngle;
        Coordinate cC = new Coordinate(
                cA.getX() + CURVE_SIZE * Math.abs(theta) * (float) Math.cos(startAngle),
                cA.getY() + CURVE_SIZE * Math.abs(theta) * (float) Math.sin(startAngle));

        ArrayList<Coordinate> arc = new ArrayList<>();
        float length = 0;
        Coordinate cPrevious = cA;
        for (float i = 0; i <= 1.0; i += PRECISION) {
            Coordinate c1 = Coordinate.coordinateAlongLine(cA, cC, i);
            Coordinate c2 = Coordinate.coordinateAlongLine(cC, cB, i);
            Coordinate c3 = Coordinate.coordinateAlongLine(c1, c2, i);
            arc.add(c3);
            length += Coordinate.distanceBetween(cPrevious, c3);
            cPrevious = c3;
        }
        length += Coordinate.distanceBetween(cPrevious, cB);

        // Work out the closest value to DISTANCE_BETWEEN_SLEEPERS that spaces evenly
        float spacing = length / (float) Math.round(length / DISTANCE_BETWEEN_SLEEPERS);
        // Use the coordinates in arc to create angled sleepers that are spaced out evenly
        ArrayList<Sleeper> sleeperArc = new ArrayList<>();
        Sleeper s = new BasicSleeper(true);
        s.setPosition((float) cA.getX(), (float) cA.getY());
        s.setRotation((float) Math.toDegrees(startAngle));
        sleeperArc.add(s);
        float d = 0;
        for (int i = 0; i + 1 < arc.size(); i++) {
            Coordinate c1 = arc.get(i);
            Coordinate c2 = arc.get(i + 1);
            d += Coordinate.distanceBetween(c1, c2);
            while (d >= spacing) {
                d -= spacing;
                float percentage = 1 - d / Coordinate.distanceBetween(c1, c2);
                float angle = Coordinate.angleBetween(c1, c2);
                Coordinate c = Coordinate.coordinateAlongLine(c1, c2, percentage);
                s = new BasicSleeper(false);
                s.setPosition(c.getX(), c.getY());
                s.setRotation((float) Math.toDegrees(angle));
                sleeperArc.add(s);
            }
        }
        return sleeperArc;
    }

    // returns an ArrayDeque listing nodes from the start node to the end node
    public List<Node> getNodes() {
        return nodes;
    }

    public List<Sleeper> getSleepers() {
        return sleepers;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Sleeper angle +90 to rotate the image to the correct orientation
        for (Sleeper s : sleepers) {
            s.draw(batch, parentAlpha);
        }
    }

}
