package com.taxe.game;

import java.util.ArrayList;

/**
 * Created by Owen on 19/11/2014.
 */
public class Map {

    private ArrayList<City> cities;
    private ArrayList<Track> tracks;

    private ArrayList<Homebase> homebases;

    public Map(ArrayList<City> cities, ArrayList<Track> tracks, ArrayList<Homebase> homebases){
        this.cities = cities;
        this.tracks = tracks;
        this.homebases = homebases;
    }

    public ArrayList<City> getCities(){
        return cities;
    }

    public ArrayList<Track> getTracks(){
        return tracks;
    }

    public ArrayList<Homebase> getHomebases() {
        return homebases;
    }

    public void draw(){
        // Draw each city and track
    }

}
