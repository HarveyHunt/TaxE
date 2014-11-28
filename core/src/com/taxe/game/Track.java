package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Owen on 18/11/2014.
 */
public class Track {

    private ArrayList<Node> track;
    private ArrayList<Sleeper> sleepers;
    private Texture sleeperTexture;

    public Track(ArrayList<Node> track){
        this.track = track;
        sleeperTexture = new Texture("sleeper.png");

        Coordinate nodeA = track.get(0).getCoordinate();
        Coordinate nodeB = track.get(1).getCoordinate();
        float theta = Coordinate.angleBetween(nodeA, nodeB);
        sleepers = new ArrayList<Sleeper>(0);
        sleepers.addAll(getArc(nodeA, nodeB, theta, 12));
        for(int i = 1; i < track.size() - 1; i++){
            nodeA = track.get(i).getCoordinate();
            nodeB = track.get(i + 1).getCoordinate();
            sleepers.addAll(getArc(nodeA, nodeB, theta, 12));
            theta = Coordinate.angleBetween(sleepers.get(sleepers.size() - 2).getPosition(), nodeB);
        }
    }

    // return a list of sleepers that form a curve between 2 nodes based on a starting direction
    private ArrayList<Sleeper> getArc(Coordinate node1, Coordinate node2, float startAngle, float interval){
        /*
         This method takes a start point, end point, and initial angle and returns a list of coordinates that follow
         the arc between the two points, spaced out by the given interval (in pixels)
         -------------------------------------
         This is where i would put the explanation of the maths, if i wasn't lazy
         -------------------------------------
          */

        // the difference between the start angle and the angle that points from node1 to node2
        double theta = Coordinate.angleBetween(node1, node2) - startAngle;
        // the 3rd point of the triangle
        int curveSize = 100; // How much curve?
        Coordinate node3 = new Coordinate(node1.getX() + curveSize * (float)Math.abs(theta) * (float)Math.cos(startAngle), node1.getY() + curveSize * (float)Math.abs(theta) * (float)Math.sin(startAngle));

        // create a list of coordinates that plot out the arc but are not evenly spaced
        // the line is divided into 1/precision number of segments
        float precision = 0.05f;
        ArrayList<Coordinate> arc = new ArrayList<Coordinate>(Math.round(1 / precision) + 1);
        arc.add(node1);
        for (float i = precision; i < 1; i += precision){
            Coordinate interval1 = percentageAlongLine(node1, node3, i);
            Coordinate interval2 = percentageAlongLine(node3, node2, i);
            arc.add(percentageAlongLine(interval1, interval2, i));
        }
        arc.add(node2);

        // use the coordinates in arc to create angled sleepers that are spaced out evenly
        // using the total length of the arc, we work out the best separation to have between sleepers (to avoid bad spacing at the end)
        float totalLength = 0;
        for(int i = 0; i < arc.size() - 1; i++){
            totalLength += Coordinate.distanceBetween(arc.get(i + 1), arc.get(i));
        }
        // set interval to be a close number that will divide perfectly into the total length
        int numberOfSleepers = Math.round(totalLength / interval);
        interval = totalLength / numberOfSleepers;
        ArrayList<Sleeper> spacedArc = new ArrayList<Sleeper>(numberOfSleepers);
        // first sleeper
        spacedArc.add(new Sleeper(node1, startAngle));
        // add intermediate sleepers
        int arcNode = 0;
        float angle = Coordinate.angleBetween(arc.get(arcNode), arc.get(arcNode + 1));
        float x = Coordinate.distanceBetween(arc.get(arcNode), arc.get(arcNode + 1));
        for(int i = 0; i < numberOfSleepers; i++){
            while(x < interval){
                if(arcNode > arc.size() - 3){
                    break;
                }
                arcNode++;
                angle = Coordinate.angleBetween(arc.get(arcNode), arc.get(arcNode + 1));
                x += Coordinate.distanceBetween(arc.get(arcNode), arc.get(arcNode + 1));
            }
            x -= interval;
            float percentage = 1 - x / Coordinate.distanceBetween(arc.get(arcNode), arc.get(arcNode + 1));
            spacedArc.add(new Sleeper(percentageAlongLine(arc.get(arcNode), arc.get(arcNode + 1), percentage), angle));
        }

        return spacedArc;
    }

    private Coordinate percentageAlongLine(Coordinate node1, Coordinate node2, float percentage){
        // returns the point a certain percentage along a line between nodes 1 and 2
        // percentage as a decimal
        float dX = node2.getX() - node1.getX(); // difference between the coordinate along the X-axis
        float dY = node2.getY() - node1.getY(); // difference between the coordinate along the Y-axis
        return new Coordinate(node1.getX() + dX * percentage, node1.getY() + dY * percentage);
    }

    // returns an ArrayDeque listing nodes from the start node to the end node
    public ArrayDeque<Node> getPath(){
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
        batch.draw(sleeperTexture, coordinate.getX(), coordinate.getY(), 12, 3, 24, 6, 1, 1, angle, 0, 0, 24, 6, false, false);
    }

    //
    public void draw(SpriteBatch batch){
        for(int i = 0; i < sleepers.size(); i++){
            // sleeper angle +90 to rotate to correct orientation
            // *180/PI converts from radians to degrees
            drawSleeper(batch, sleepers.get(i).getPosition(), (float)sleepers.get(i).getAngle() * 180 / (float)Math.PI + 90);
        }
    }


}
