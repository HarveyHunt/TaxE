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

    // constants
    private final float INTERVAL;
    private final int CURVE_SIZE;
    private final float PRECISION;
    private final int TEX_WIDTH;
    private final int TEX_HEIGHT;

    private ArrayList<Node> track;
    private ArrayList<Sleeper> sleepers;
    private Texture sleeperTexture;

    public Track(ArrayList<Node> track){
        this.track = track;
        sleeperTexture = new Texture("sleeper.png");

        // define constants
        TEX_WIDTH = sleeperTexture.getWidth();
        TEX_HEIGHT = sleeperTexture.getHeight();
        INTERVAL = 12;
        CURVE_SIZE = 100;
        PRECISION = 0.05f;

        sleepers = new ArrayList<Sleeper>(0);
        if(track.size() >= 2){
            Coordinate cA = track.get(0).getCoordinate();
            Coordinate cB = track.get(1).getCoordinate();
            float startAngle = Coordinate.angleBetween(cA, cB);
            sleepers.addAll(getArc(cA, cB, startAngle));
            for(int i = 1; i < track.size() - 1; i++){
                cA = track.get(i).getCoordinate();
                cB = track.get(i + 1).getCoordinate();
                sleepers.addAll(getArc(cA, cB, startAngle));
                startAngle = Coordinate.angleBetween(sleepers.get(sleepers.size() - 2).getPosition(), cB);
            }
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
        xLine is represented by the method percentageAlongLine(A, B, x)
    ---------------------------------
     */
    // return a list of sleepers that form a curve between 2 nodes based on a starting direction
    private ArrayList<Sleeper> getArc(Coordinate coordinateA, Coordinate coordinateB, float startAngle){
        // the difference between the start angle and the angle that points from node1 to node2
        double theta = Coordinate.angleBetween(coordinateA, coordinateB) - startAngle;
        // the 3rd point of the triangle
        Coordinate coordinateC = new Coordinate(coordinateA.getX() + CURVE_SIZE * (float)Math.abs(theta) * (float)Math.cos(startAngle), coordinateA.getY() + CURVE_SIZE * (float)Math.abs(theta) * (float)Math.sin(startAngle));

        // create a list of coordinates that plot out the arc but are not evenly spaced
        // the line is divided into 1/precision number of segments
        ArrayList<Coordinate> arc = new ArrayList<Coordinate>(Math.round(1 / PRECISION) + 1);
        arc.add(coordinateA);
        for (float i = PRECISION; i < 1; i += PRECISION){
            Coordinate interval1 = percentageAlongLine(coordinateA, coordinateC, i);
            Coordinate interval2 = percentageAlongLine(coordinateC, coordinateB, i);
            arc.add(percentageAlongLine(interval1, interval2, i));
        }
        arc.add(coordinateB);

        // use the coordinates in arc to create angled sleepers that are spaced out evenly
        // using the total length of the arc, we work out the best separation to have between sleepers (to avoid bad spacing at the end)
        float totalLength = 0;
        for(int i = 0; i < arc.size() - 1; i++){
            totalLength += Coordinate.distanceBetween(arc.get(i + 1), arc.get(i));
        }
        // set interval to be a close number that will divide perfectly into the total length
        int numberOfSleepers = Math.round(totalLength / INTERVAL);
        float interval = totalLength / numberOfSleepers;
        ArrayList<Sleeper> sleeperArc = new ArrayList<Sleeper>(numberOfSleepers);
        // first sleeper
        sleeperArc.add(new Sleeper(coordinateA, startAngle));
        // add intermediate sleepers
        int arcPoint = 0; // which previously calculated point along the Bezier curve are we looking at currently
        float angle = Coordinate.angleBetween(arc.get(arcPoint), arc.get(arcPoint + 1));
        float x = Coordinate.distanceBetween(arc.get(arcPoint), arc.get(arcPoint + 1));
        for(int i = 0; i < numberOfSleepers; i++){
            while(x < interval & arcPoint < arc.size() - 2){
                arcPoint++;
                angle = Coordinate.angleBetween(arc.get(arcPoint), arc.get(arcPoint + 1));
                x += Coordinate.distanceBetween(arc.get(arcPoint), arc.get(arcPoint + 1));
            }
            x -= interval;
            float percentage = 1 - x / Coordinate.distanceBetween(arc.get(arcPoint), arc.get(arcPoint + 1));
            sleeperArc.add(new Sleeper(percentageAlongLine(arc.get(arcPoint), arc.get(arcPoint + 1), percentage), angle));
        }

        return sleeperArc;
    }

    private Coordinate percentageAlongLine(Coordinate coordinate0, Coordinate coordinate1, float percentage){
        // returns the point a certain percentage along a line between nodes 1 and 2
        // percentage as a decimal between 0 and 1.
        float dX = coordinate1.getX() - coordinate0.getX(); // difference between the coordinate along the X-axis
        float dY = coordinate1.getY() - coordinate0.getY(); // difference between the coordinate along the Y-axis
        return new Coordinate(coordinate0.getX() + dX * percentage, coordinate0.getY() + dY * percentage);
    }

    // returns an ArrayDeque listing nodes from the start node to the end node
    public ArrayDeque<Node> getPath() {
        return new ArrayDeque<Node>(track);
    }

    // returns an ArrayDeque listing nodes from the end node to the start node
    public ArrayDeque<Node> getReversedPath(){
        ArrayList<Node> track = this.track;
        Collections.reverse(track);
        return new ArrayDeque<Node>(track);
    }

    // returns the very first node
    public Node getStartNode(){
        return track.get(0);
    }

    // returns the very last node
    public Node getEndNode(){
        return track.get(track.size() - 1);
    }

    // draw an individual sleeper
    public void drawSleeper(SpriteBatch batch, Coordinate coordinate, float angle){
        batch.draw(sleeperTexture, coordinate.getX(), coordinate.getY(), TEX_WIDTH / 2, TEX_HEIGHT / 2, TEX_WIDTH, TEX_HEIGHT, 1, 1, angle, 0, 0, TEX_WIDTH, TEX_HEIGHT, false, false);
    }

    //
    public void draw(SpriteBatch batch){
        for(int i = 0; i < sleepers.size(); i++){
            // sleeper angle +90 to rotate the image to the correct orientation
            // *180/PI converts from radians to degrees
            drawSleeper(batch, sleepers.get(i).getPosition(), (float)sleepers.get(i).getAngle() * 180 / (float)Math.PI + 90);
        }
    }


}
