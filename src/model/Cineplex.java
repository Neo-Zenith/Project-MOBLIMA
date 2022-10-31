package model;

import java.util.ArrayList;

public class Cineplex {
    private String UUID;
    private String cineplexName;
    private int numOfCinemas;
    private ArrayList <Cinema> cinemas;

    public Cineplex(String UUID, String cineplexName, int numOfCinemas, ArrayList <Cinema> cinemas) {
        this.setUUID(UUID);
        this.setCineplexName(cineplexName);
        this.setNumOfCinemas(numOfCinemas);
        this.setCinemas(cinemas);
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getCineplexName() {
        return this.cineplexName;
    }

    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    public int getNumOfCinemas() {
        return this.numOfCinemas;
    }

    public void setNumOfCinemas(int numOfCinemas) {
        this.numOfCinemas = numOfCinemas;
    }

    public ArrayList <Cinema> getCinemas() {
        return this.cinemas;
    }

    public void setCinemas(ArrayList <Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public void addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
        this.numOfCinemas ++;
    }
}
