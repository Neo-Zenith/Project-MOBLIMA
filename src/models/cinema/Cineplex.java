package models.cinema;

public class Cineplex {
    public Cinema cinema[];
    public String cineplexName;
    public int numOfCinema;

    /*
     * Constructor
     */
    public Cineplex(String cineplexName, int numOfCinema) {
        this.cineplexName = cineplexName;
        this.numOfCinema = numOfCinema;
        this.cinema = new Cinema[this.numOfCinema];
    }

    /*
     * Returns the cineplex name
     */
    public String getCineplexName() {
        return this.cineplexName;
    }

    /*
     * Returns the number of cinema in the cineplex
     */
    public int getNumOfCinema() {
        return this.numOfCinema;
    }

    /*
     * Returns a list of cinema objects instantiated in the cineplex
     */
    public Cinema[] getListofCinema() {
        return this.cinema;
    }

    /*
     * Sets the cineplex name
     */
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /*
     * Sets the number of cinemas in the cineplex
     */
    public void setNumOfCinema(int numOfCinema) {
        this.numOfCinema = numOfCinema;
        this.cinema = new Cinema[this.numOfCinema];
    }

    public void populateCineplex(Cinema cinema[]) {
        for (int i = 0; i < this.numOfCinema; i ++) {
            this.cinema[i] = cinema[i];
        }
    }
}
