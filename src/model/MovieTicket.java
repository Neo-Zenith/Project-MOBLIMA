package src.model;

import java.io.Serializable;

/**
 * The Class that handles the data of Movie Ticket
 * 
 * @author Ci Hui
 * @version 1.0
 */
public class MovieTicket implements Serializable{

    /**
     * Unique ID of the Movie Ticket
     */
    private String UUID;

    /**
     * Seat ID of the chosen Seat
     */
    private String seatID;

    /**
     * Unique ID of the chosen Movie
     */
    private String movieUUID;

    /**
     * Showing Time chosen for the Movie
     */
    private DateTime showTime;

    /**
     * Unique ID of the chosen Cinema
     */
    private String cinemaUUID;

    /**
     * For Java Serializable
     */
    private final static long serialVersionUID = 14L;

    /**
     * Constructor for the MovieTicket Class
     * 
     * @param UUID          is the unique ID in the database
     * @param movieUUID     is the unique ID of the chosen Movie
     * @param showTime      is the Showing Time of the chosen Movie
     * @param seatID        is the Seat ID chosen
     * @param cinemaUUID    is the unique ID of the chosen Cinema
     */
    public MovieTicket(String UUID, String movieUUID, DateTime showTime, String seatID, String cinemaUUID) {
        this.setUUID(UUID);
        this.setSeatID(seatID);
        this.setMovieToWatch(movieUUID);
        this.setShowTime(showTime);
        this.setShowingVenue(cinemaUUID);
    }

    /**
     * Gets the unique ID of the Movie Ticket
     * 
     * @return unique ID of the Movie Ticket
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Set the unique ID of the Movie Ticket
     * 
     * @param UUID is the unique ID of the Movie Ticket to be set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the unique ID of the chosen Movie
     * 
     * @return the unique ID of the chosen Movie
     */
    public String getMovieToWatch() {
        return this.movieUUID;
    }

    /**
     * Sets the unique ID of the chosen Movie
     * 
     * @param movieToWatch the unique ID of the chosen Movie to be set
     */
    public void setMovieToWatch(String movieToWatch) {
        this.movieUUID = movieToWatch;
    }

    /**
     * Gets the Showing Time of the chosen Movie
     * 
     * @return the Showing Time of the chosen Movie
     */
    public DateTime getShowTime() {
        return this.showTime;
    }

    /**
     * Sets the Showing Time of the chosen Movie
     * 
     * @param showTime is the Showing Time of the chosen Movie to be set
     */
    public void setShowTime(DateTime showTime) {
        this.showTime = showTime;
    }

    /**
     * Gets the unique ID of the chosen Cinema where the Moive is going to show
     * 
     * @return the unique ID of the chosen Cinema where the Moive is going to show
     */
    public String getShowingVenue() {
        return this.cinemaUUID;
    }

    /**
     * Sets the unique ID of the chosen Cinema where the Moive is going to show
     * 
     * @param showingVenue is the unique ID of the chosen Cinema where the Moive is going to show
     */
    public void setShowingVenue(String showingVenue) {
        this.cinemaUUID = showingVenue;
    }

    /**
     * Gets the Seat ID of chosen Seat
     * 
     * @return the Seat ID of chosen Seat
     */
    public String getSeatID() {
        return seatID;
    }

    /**
     * Sets the Seat ID of the chosen Seat
     * 
     * @param seatID is the Seat ID of the chosen Seat
     */
    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }
}
