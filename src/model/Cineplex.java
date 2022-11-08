package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Lee Juin
 * @version 1.0
 */
public class Cineplex implements Comparable, Serializable{
    private String UUID;
    private String cineplexName;
    private String cineplexLocation;
    private int numOfCinemas;
    private ArrayList <Cinema> cinemas;
    private static final long serialVersionUID = 2L;

    /**
     * Constructor for Cineplex class
     * @param UUID is the unique ID in the database
     * @param cineplexName is the name of the cinplex
     * @param numOfCinemas is the total number of cinemas in the cineplex
     * @param cinemas is all the {@link Cinema} instances in the cineplex
     * @param cineplexLocation is the location of the cineplex
     */
    public Cineplex(String UUID, String cineplexName, int numOfCinemas, ArrayList <Cinema> cinemas, String cineplexLocation) {
        this.setUUID(UUID);
        this.setCineplexName(cineplexName);
        this.setNumOfCinemas(numOfCinemas);
        this.setCinemas(cinemas);
        this.setCineplexLocation(cineplexLocation);
    }

    /**
     * Gets the unique ID of the cineplex
     * @return The unique ID of the cineplex
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Sets the unique ID of the cineplex
     * @param UUID is the unique ID of the cineplex
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the name of the cineplex
     * @return The name of the cineplex
     */
    public String getCineplexName() {
        return this.cineplexName;
    }

    /**
     * Sets the name of the cineplex
     * @param cineplexName is the name of the cineplex
     */
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /**
     * Gets the total number of cinemas in the cineplex
     * @return The total number of cinemas in the cineplex
     */
    public int getNumOfCinemas() {
        return this.numOfCinemas;
    }

    /**
     * Sets the total number of cinemas in the cineplex
     * @param numOfCinemas is the total number of cinemas in the cineplex
     */
    public void setNumOfCinemas(int numOfCinemas) {
        this.numOfCinemas = numOfCinemas;
    }

    /**
     * Gets all the {@Link Cinema} instances in the cineplex
     * @return ArrayList of {@Link Cinema} instances in the cineplex
     */
    public ArrayList <Cinema> getCinemas() {
        return this.cinemas;
    }

    /**
     * Sets all the {@Link Cinema} instances in the cineplex
     * @param cinemas is the {@Link Cinema} instances to be set
     */
    public void setCinemas(ArrayList <Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    /**
     * Method to add a cinema into the cineplex
     * @param cinema is the cinema to be added
     */
    public void addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
        this.numOfCinemas ++;
    }

    /**
     * Method to compare between cineplexes, sorted by UUID
     * @param object is the cineplex to be compared to
     * @return More than zero if object is lower priority, equal to zero if equal priority, less than zero if object is higher priority
     */
    @Override 
    public int compareTo(Object object) {
        Cineplex cineplex = (Cineplex) object;
        return this.getUUID().compareTo(cineplex.getUUID());
    }

    /**
     * Gets the location of the cineplex
     * @return The location of the cineplex
     */
    public String getCineplexLocation() {
        return this.cineplexLocation;
    }

    /**
     * Sets the location of the cineplex
     * @param cineplexLocation is the location of the cineplex
     */
    public void setCineplexLocation(String cineplexLocation) {
        this.cineplexLocation = cineplexLocation;
    }
}
