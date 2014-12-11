package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Owen on 18/11/2014.
 */
public class Track {

    // Constants for drawing
    private static final double DISTANCE_BETWEEN_SLEEPERS = 12.0;
    private static final double PRECISION = 0.02;
    private static final int CURVE_SIZE = 100;
    private final int texWidth;
    private final int texHeight;
    private final Texture sleeperTexture;

    private final ArrayList<Node> track;
    private final ArrayList<Sleeper> sleepers;


    public Track(ArrayList<Node> track, Texture sleeperTexture) {
        this.track = track;
        this.sleeperTexture = sleeperTexture;
        this.texWidth = sleeperTexture.getWidth();
        this.texHeight = sleeperTexture.getHeight();
        this.sleepers = new ArrayList<>();

        for (int i = 0; i < track.size() - 1; i++) {
            Coordinate cA = track.get(i).getCoordinate();
            Coordinate cB = track.get(i + 1).getCoordinate();
            double startAngle = (i == 0) ?
                    Coordinate.angleBetween(cA, cB) :
                    Coordinate.angleBetween(sleepers.get(sleepers.size() - 2).getCoordinate(), cA);
            sleepers.addAll(getArc(cA, cB, startAngle));
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


    // Returns a list of sleepers that form a curve between 2 nodes based on a starting direction
    private ArrayList<Sleeper> getArc(Coordinate cA, Coordinate cB, double startAngle) {

        // Create a list of coordinates that plot out the arc but are not evenly spaced
        // The line is divided into 1 / precision number of segments
        double theta = Coordinate.angleBetween(cA, cB) - startAngle;
        Coordinate cC = new Coordinate(
                cA.getX() + CURVE_SIZE * Math.abs(theta) * Math.cos(startAngle),
                cA.getY() + CURVE_SIZE * Math.abs(theta) * Math.sin(startAngle));

        ArrayList<Coordinate> arc = new ArrayList<>();
        for (double i = 0; i <= 1.0; i += PRECISION) {
            Coordinate c1 = coordinateAlongLine(cA, cC, i);
            Coordinate c2 = coordinateAlongLine(cC, cB, i);
            arc.add(coordinateAlongLine(c1, c2, i));
        }

        // Use the coordinates in arc to create angled sleepers that are spaced out evenly
        ArrayList<Sleeper> sleeperArc = new ArrayList<>();
        sleeperArc.add(new Sleeper(cA, startAngle));
        double d = 0.0;
        for (int i = 0; i + 1 < arc.size(); i++) {
            Coordinate c1 = arc.get(i);
            Coordinate c2 = arc.get(i + 1);
            d += Coordinate.distanceBetween(c1, c2);
            if (d >= DISTANCE_BETWEEN_SLEEPERS) {
                d -= DISTANCE_BETWEEN_SLEEPERS;
                double percentage = 1.0 - d / Coordinate.distanceBetween(c1, c2);
                double angle = Coordinate.angleBetween(c1, c2);
                sleeperArc.add(new Sleeper(coordinateAlongLine(c1, c2, percentage), angle));
            }
        }
        return sleeperArc;
    }

    private Coordinate coordinateAlongLine(Coordinate cA, Coordinate cB, double percentage) {
        // Returns the point a certain percentage along a line between nodes 1 and 2
        // percentage as a decimal between 0 and 1.
        double dX = cB.getX() - cA.getX();
        double dY = cB.getY() - cA.getY();
        return new Coordinate(cA.getX() + dX * percentage, cA.getY() + dY * percentage);
    }

    // returns an ArrayDeque listing nodes from the start node to the end node
    public ArrayDeque<Node> getPath() {
        return new ArrayDeque<Node>(track);
    }

    // returns an ArrayDeque listing nodes from the end node to the start node
    public ArrayDeque<Node> getReversedPath() {
        ArrayList<Node> track = this.track;
        Collections.reverse(track);
        return new ArrayDeque<Node>(track);
    }

    public void draw(SpriteBatch batch) {
        // Sleeper angle +90 to rotate the image to the correct orientation
        for (Sleeper s : sleepers) {
            batch.draw(
                    sleeperTexture, (float) s.getCoordinate().getX(), (float) s.getCoordinate().getY(),
                    texWidth / 2, texHeight / 2, texWidth, texHeight, 1.0f, 1.0f,
                    (float) Math.toDegrees(s.getAngle()) + 90,
                    0, 0, texWidth, texHeight, false, false
            );
        }
    }

}
