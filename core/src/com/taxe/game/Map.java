package com.taxe.game;

import java.util.ArrayList;

/**
 * Created by Owen on 19/11/2014.
 */
public class Map {

    private ArrayList<City> cities;
    private ArrayList<Track> tracks;

    public Map(){
        this.cities = new ArrayList<City>();
        this.tracks = new ArrayList<Track>();
    }

    public ArrayList<City> getCities(){
        return cities;
    }

    public ArrayList<Track> getTracks(){
        return tracks;
    }

    public void draw(){
        // Draw each city and track
    }

}
