package models.cinema;

public class Cineplex {
    public Cinema cinema[];
    public String cineplexName;
    public int numOfCinema;

    public Cineplex(String cineplexName, int numOfCinema) {
        this.cineplexName = cineplexName;
        this.numOfCinema = numOfCinema;
        this.cinema = new Cinema[this.numOfCinema];
    }

    public String getCineplexName() {
        return this.cineplexName;
    }

    public int getNumOfCinema() {
        return this.numOfCinema;
    }

    public Cinema[] getListofCinema() {
        return this.cinema;
    }

    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    public void setNumOfCinema(int numOfCinema) {
        this.numOfCinema = numOfCinema;
        this.cinema = new Cinema[this.numOfCinema];
    }
}
