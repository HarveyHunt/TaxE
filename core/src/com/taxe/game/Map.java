package com.taxe.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by Owen on 19/11/2014.
 */
public class Map extends Group {

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
        this.addListener(new MapClickListener());
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

    private void changeNeighbourTexture(Node n, int oldTexture, int newTexture) {
        for (Track t : tracks) {
            if (t.getPath().contains(n)) {
                for (Node nt : t.getPath()) {
                    if (nt.getCurrentTexture() == oldTexture) {
                        nt.setCurrentTexture(newTexture);
                    }
                }
            }
        }
    }

    private void changeAllTextures(int oldTexture, int newTexture) {
        for (Track t : tracks) {
            for (Node nt : t.getPath()) {
                if (nt.getCurrentTexture() == oldTexture)
                    nt.setCurrentTexture(newTexture);
            }
        }
    }

    private void changeTrackTexture(int fillTexture, int startTexture, int endTexture) {
        for (Track t : tracks) {
            int t0 = t.getPath().getFirst().getCurrentTexture();
            int t1 = t.getPath().getLast().getCurrentTexture();
            if ((t0 == startTexture && t1 == endTexture) || (t0 == endTexture && t1 == startTexture)) {
                for (Node nt : t.getPath()) {
                    nt.setCurrentTexture(fillTexture);
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.drawChildren(batch, parentAlpha);
    }

    private class MapClickListener extends ClickListener {
        private ArrayDeque<Node> trainPath;

        @Override
        public void clicked(InputEvent event, float x, float y) {
            Actor target = event.getTarget();
            if (target instanceof Node) {
                Node n = (Node) target;
                System.out.println(n.getCurrentTexture());
                if (n.getCurrentTexture() == Textures.ORIGINAL) {
                    n.setCurrentTexture(Textures.SELECTED);
                    changeNeighbourTexture(n, Textures.ORIGINAL, Textures.HIGHLIGHTED);
                    trainPath = new ArrayDeque<>();
                    trainPath.add(n);
                } else if (n.getCurrentTexture() == Textures.HIGHLIGHTED) {
                    changeAllTextures(Textures.HIGHLIGHTED, Textures.ORIGINAL);
                    n.setCurrentTexture(Textures.SELECTED);
                    changeTrackTexture(Textures.SELECTED, Textures.SELECTED, Textures.SELECTED);
                    changeNeighbourTexture(n, Textures.ORIGINAL, Textures.HIGHLIGHTED);
                    trainPath.add(n);
                } else if (n.getCurrentTexture() == Textures.SELECTED) {
                    while (trainPath.getLast() != n) {
                        Node c = trainPath.getLast();
                        changeNeighbourTexture(c, Textures.HIGHLIGHTED, Textures.ORIGINAL);
                        changeNeighbourTexture(c, Textures.SELECTED, Textures.ORIGINAL);
                        trainPath.removeLast();
                    }
                    n.setCurrentTexture(Textures.SELECTED);
                    changeNeighbourTexture(n, Textures.ORIGINAL, Textures.HIGHLIGHTED);
                }
            }
        }
    }

}
