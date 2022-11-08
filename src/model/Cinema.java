package src.model;

import java.util.*;
import java.io.Serializable;
import src.model.enums.*;

/**
 * @author Lee Juin
 * @version 1.0
 */
public abstract class Cinema implements Comparable, Serializable{
    
    private String UUID;
    private CinemaClass cinemaClass;
    private ArrayList <Seat> seats;
    private int numOfRows;
    private int totalNumOfSeats;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Cinema class
     * @param UUID is the unique ID in the database
     * @param seats is all the {@Link Seat} instances in the cinema
     * @param numOfRows is the total number of rows in the cinema
     * @param totalNumOfSeats is the total number of seats in the cinema
     */
    public Cinema(String UUID, ArrayList <Seat> seats, int numOfRows, int totalNumOfSeats) {
        this.setUUID(UUID);
        this.setSeats(seats);
        this.setNumOfRows(numOfRows);
        this.setTotalNumOfSeats(totalNumOfSeats);
    }

    /**
     * Gets the unique ID of the cinema
     * @return Unique ID of the cinema
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Sets the unique ID of the cinema
     * @param UUID is the unique ID to be set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the class of the cinema
     * @return {@link CinemaClass} of the cinema
     */
    public CinemaClass getCinemaClass() {
        return this.cinemaClass;
    }

    /**
     * Sets the class of the cinema
     * @param cinemaClass is the {@Link CinemaClass} to be set
     */
    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * Gets all the {@Link Seat} instances in the cinema
     * @return ArrayList of {@link Seat}
     */
    public ArrayList <Seat> getSeats() {
        return this.seats;
    }

    /**
     * Method to make a copy of the seats in the cinema
     * @return ArrayList of {@link Seat}
     */
    public ArrayList <Seat> duplicateSeats() {
        ArrayList <Seat> seats = new ArrayList<>();
        seats = this.seats;
        return seats;
    }

    /**
     * Sets all the {@Link Seat} instances in the cinema
     * @param seats is the ArrayList of {@Link Seat} instances to be set
     */
    public void setSeats(ArrayList <Seat> seats) {
        this.seats = seats;
    }

    /**
     * Method to add a seat into the cinema
     * @param seat is the {@Link Seat} instance to be added
     */
    public void addSeat(Seat seat) {
        this.seats.add(seat);
        this.totalNumOfSeats ++;
    }

    /**
     * Gets the price weight of the cinema
     * @return Price weight of the cinema
     */
    public abstract double getCinemaPrice();

    /**
     * Sets the price weight of the cinema
     * @param cinemaPrice is the price weight of the cinema to be set
     */
    public abstract void setCinemaPrice(double cinemaPrice);

    /**
     * Gets the total number of rows of seats in the cinema
     * @return The total number of rows
     */
    public int getNumOfRows() {
        return this.numOfRows;
    }

    /**
     * Sets the total number of rows of seats in the cinema
     * @param numOfRows is the total number of rows to be set
     */
    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    /**
     * Gets the total number of seats in the cinema
     * @return The total number of seats
     */
    public int getTotalNumOfSeats() {
        return this.totalNumOfSeats;
    }

    /**
     * Sets the total number of seats in the cinema
     * @param totalNumOfSeats is the total number of seats to be set
     */
    public void setTotalNumOfSeats(int totalNumOfSeats) {
        this.totalNumOfSeats = totalNumOfSeats;
    }

    /**
     * Method to compare between cinemas, sorted by UUID
     * @param object is the cinema to be compared to
     * @return More than zero if object is lower priority, equal to zero if equal priority, less than zero if object is higher priority
     */
    @Override 
    public int compareTo(Object object) {
        Cinema cinema = (Cinema) object;
        return this.getUUID().compareTo(cinema.getUUID());
    }
}
