package src.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Movie Schedule class
 * @author Lee Juin
 * @version 1.0
 */
public class MovieSchedule implements Serializable {

    /**
     * The unique ID of the movie schedule instance
     */
    private String UUID;

    /**
     * The unique ID of the movie belonging to the current movie schedule
     */
    private String movieUUID;

    /**
     * All the showing venues currently showing the movie
     */
    private ArrayList <String> showingVenueUUID;

    /**
     * The seating plan of each showing venue
     */
    private ArrayList <ArrayList <Seat>> seatingPlan;

    /**
     * The showing time for each showing
     */
    private ArrayList <DateTime> showingTime;
    private static final long serialVersionUID = 5L;

    /**
     * Constructor
     * @param UUID is the unique ID of the instance in the databasee
     * @param movieUUID is the unique ID of the movie to be shown
     * @param showingVenueUUID is the unique ID of all the showing venues showing the movie
     * @param seatingPlan is the corresponding seating plan of the showing venue
     * @param showingTime is the corresponding showing time
     */
    public MovieSchedule(String UUID, String movieUUID, ArrayList <String> showingVenueUUID, 
                        ArrayList <ArrayList <Seat>> seatingPlan, ArrayList <DateTime> showingTime) {
            this.setUUID(UUID);
            this.setMovieOnShow(movieUUID);
            this.setShowingVenues(showingVenueUUID);
            this.setSeatingPlan(seatingPlan);
            this.setShowingTime(showingTime);
    }

    /**
     * Gets the unique ID of a {@link MovieSchedule} isntance 
     * @return The unique ID
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Sets the unique ID of a {@link MovieSchedule} isntance 
     * @param UUID The unique ID
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the UUID of the movie to be shown
     * @return The UUID of the movie to be shown
     */
    public String getMovieOnShow() {
        return this.movieUUID;
    }

    /**
     * Sets the UUID of the movie to be shown
     * @param movieUUID is the UUID of the movie to be shwon
     */
    public void setMovieOnShow(String movieUUID) {
        this.movieUUID = movieUUID;
    }

    /**
     * Gets all the showing venue UUID showing the movie
     * @return ArrayList of UUID of {@link Cinema} instances showing the movie
     */
    public ArrayList<String> getShowingVenues() {
        return this.showingVenueUUID;
    }

    /**
     * Sets all the showing venue UUID showing the movie
     * @param showingVenueUUID ArrayList of UUID of {@link Cinema} instances showing the movie
     */
    public void setShowingVenues(ArrayList<String> showingVenueUUID) {
        this.showingVenueUUID = showingVenueUUID;
    }

    /**
     * Method to add a showing venue to the schedule
     * @param cinemaUUID is the UUID of the showing venue to be added
     */
    public void addShowingVenue(String cinemaUUID) {
        this.showingVenueUUID.add(cinemaUUID);
    }

    /**
     * Gets the seating plan of all the showing venues showing the movie
     * @return The seating plan of all the showing venues showing the movie
     */
    public ArrayList<ArrayList<Seat>> getSeatingPlan() {
        return this.seatingPlan;
    }

    /**
     * Sets the seating plan of all the showing venues showing the movie
     * @param seatingPlan is the seating plan of all the showing venues showing the movie
     */
    public void setSeatingPlan(ArrayList<ArrayList<Seat>> seatingPlan) {
        this.seatingPlan = seatingPlan;
    }

    /**
     * Adds a seating plan to the schedule. Used concurrently when adding a showing venue
     * @param seatingPlan is the seating plan to be added
     */
    public void addSeatingPlan(ArrayList <Seat> seatingPlan) {
        this.seatingPlan.add(seatingPlan);
    }

    /**
     * Removes a seating plan from the schedule. Used concurrently when removing a showing venue.
     * @param index is the index of the showing venue to be removed
     */
    public void removeSeatingPlan(int index) {
        this.getSeatingPlan().remove(index);
    }

    /**
     * Method to set a seat to be occupied/unoccupied in the seating plan
     * @param venueSlot is the index of the showing venue the user has selected
     * @param seat is the seat the user has selected
     * @param assign {@code true} if the user is booking, {@code false} if the seat is being unbooked
     */
    public void setSeatStatus(int venueSlot, Seat seat, boolean assign) {
        ArrayList<Seat> seats = this.seatingPlan.get(venueSlot);

        int index = seats.indexOf(seat);
        this.seatingPlan.get(venueSlot).get(index).setAssignStatus(assign);
    }

    /**
     * Gets all the showing time for the corresponding showing venues in the schedule
     * @return ArrayList of {@link DateTime} showing time instances
     */
    public ArrayList <DateTime> getShowingTime() {
        return this.showingTime;
    }

    /**
     * Sets all the showing time for the corresponding showing venues in the schedule
     * @param showingTime is an ArrayList of {@link DateTime} showing time instances to be added
     */
    public void setShowingTime(ArrayList <DateTime> showingTime) {
        this.showingTime = showingTime;
    }


    /**
     * Adds a showing time into the schedule. Used concurrently when adding a showing venue
     * @param showingTime is the showing time to be added
     */
    public void addShowingTime(DateTime showingTime) {
        this.showingTime.add(showingTime);
    }
    

    /**
     * Removes a showing time from the schedule. Used concurrently when removing a showing venue.
     * @param index is the index of the showing venue to be removed
     */
    public void removeShowingVenue(int index){
        this.getShowingVenues().remove(index);
    }

    /**
     * Removes a showing venue from the schedule.
     * @param index is the index of the showing venue to be removed
     */
    public void removeShowingTime(int index){
        this.getShowingTime().remove(index);
    }
}
