package models.cinema;
import java.util.*;

class Cineplex {
    private String name;
    private int numOfCinema;
    private Cinema cinema[];

    public Cineplex(String name, int numOfCinema) {
        this.name = name;
        this.numOfCinema = numOfCinema;
        this.cinema = new Cinema[numOfCinema];
    }

    public String getCineplexName() {
        return this.name;
    }

    public int getNumOfCinema() {
        return this.numOfCinema;
    }

    public void setCineplexName(String name) {
        this.name = name;
    }

    public void setNumOfCinema(int numOfCinema) {
        this.numOfCinema = numOfCinema;
    }

    public void populateCinema( List <CinemaType> cinemaType,
                                int totalNumOfSeats[],
                                float cinemaPrice[],
                                int numOfRows[],
                                int numOfSeatsPerRow[][] ) {
        
        for (int i = 0; i < this.numOfCinema; i ++) {
            this.cinema[i] = new Cinema(i, totalNumOfSeats[i], cinemaType.get(i), 
                                        cinemaPrice[i], numOfRows[i], numOfSeatsPerRow[i]);
        }
    }

    public Cinema getCinema(int cinemaID) {
        return this.cinema[cinemaID];
    }
}

