package src.model;

import java.io.Serializable;
import java.util.*;

/**
 * The Class that handles the data of Booking History
 * 
 * @author Ci Hui
 * @version 1.0
 */

public class BookingHistory implements Serializable {
    /**
     * Unique ID of the Booking History
     */
    private String UUID;

    /**
     * ArrayList of {@link MovieTicket}
     */
    private ArrayList<MovieTicket> movieTicket;

    /**
     * {@link Payment} made by the MovieGoer for the Booking
     */
    private Payment payment;

    /**
     * For Java Serializable
     */
    private final static long serialVersionUID = 13L;

    /**
     * Constructor for the BookingHistory Class
     * 
     * @param UUID        is the unique ID in the database
     * @param movieTicket is the {@link MovieTicket} booked by the MovieGoer
     * @param payment     is the {@link Payment} made my the MovieGoer for the
     *                    Booking
     */
    public BookingHistory(String UUID, ArrayList<MovieTicket> movieTicket, Payment payment) {
        this.setMovieTicket(movieTicket);
        this.setPayment(payment);
        this.setUUID(UUID);
    }

    /**
     * Gets the unique ID of the Booking History
     * 
     * @return the unique ID of the Booking History
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Set the unique ID of the Booking History
     * 
     * @param UUID is the unique ID of the Booking History to be set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the ArrayList of {@link MovieTicket} booked by the MovieGoer
     * 
     * @return the ArrayList of {@link MovieTicket} booked by the MovieGoer
     */
    public ArrayList<MovieTicket> getMovieTicket() {
        return this.movieTicket;
    }

    /**
     * Sets the ArrayList of {@link MovieTicket} to be booked by the MovieGoer
     * 
     * @param movieTicket is the ArrayList of {@link MovieTicket} to be booked by
     *                    the MovieGoer
     */
    public void setMovieTicket(ArrayList<MovieTicket> movieTicket) {
        this.movieTicket = movieTicket;
    }

    /**
     * get the {@link Payment} made by the MovieGoer for the Booking
     * 
     * @return the {@link Payment} made by the MovieGoer for the Booking
     */
    public Payment getPayment() {
        return this.payment;
    }

    /**
     * set the {@link Payment} to be made by the MovieGoer for the Booking
     * 
     * @param payment is the {@link Payment} to be made by the MovieGoer for the
     *                Booking
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
