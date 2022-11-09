package src.model;

import java.util.ArrayList;
import src.database.*;
import src.model.enums.*;

/**
 * @author Lee Juin
 * @version 1.0
 */
public class StandardCinema extends Cinema {
    private double cinemaPrice;

    /**
     * Constructor for StandardCinema class
     * 
     * @param UUID            is the unique ID in the database
     * @param seats           is all the {@Link Seat} instances in the cinema
     * @param numOfRows       is the total number of rows in the cinema
     * @param totalNumOfSeats is the total number of seats in the cinema
     */
    public StandardCinema(String UUID, ArrayList<Seat> seats, int numOfRows, int totalNumOfSeats) {
        super(UUID, seats, numOfRows, totalNumOfSeats);
        this.setCinemaClass(CinemaClass.STANDARD);
        this.setCinemaPrice(Database.PRICES.getDefaultStandardCinemaPrice());
    }

    /**
     * Sets the price weight of the cinema
     * 
     * @param cinemaPrice is the price weight of the cinema
     */
    public void setCinemaPrice(double cinemaPrice) {
        this.cinemaPrice = cinemaPrice;
    }

    /**
     * Gets the price weight of the cinema
     * 
     * @return The price weight of the cinema
     */
    public double getCinemaPrice() {
        return this.cinemaPrice;
    }
}
