package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieSchedule implements Serializable {
    
    private String UUID;
    private Movie movieOnShow;
    private ArrayList <Cinema> showingVenue;
    private ArrayList <ArrayList <Seat>> seatingPlan;
    private DateTime showingTime;
    private static final long serialVersionUID = 5L;

    public MovieSchedule(String UUID, Movie movieOnShow, ArrayList <Cinema> showingVenue, 
                        ArrayList <ArrayList <Seat>> seatingPlan, DateTime showingTime) {
            this.setUUID(UUID);
            this.setMovieOnShow(movieOnShow);
            this.setShowingVenues(showingVenue);
            this.setSeatingPlan(seatingPlan);
            this.setShowingTime(showingTime);
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Movie getMovieOnShow() {
        return this.movieOnShow;
    }

    public void setMovieOnShow(Movie movieOnShow) {
        this.movieOnShow = movieOnShow;
    }

    public ArrayList <Cinema> getShowingVenues() {
        return this.showingVenue;
    }

    public void setShowingVenues(ArrayList <Cinema> showingVenue) {
        this.showingVenue = showingVenue;
    }

    public void addShowingVenue(Cinema cinema) {
        this.showingVenue.add(cinema);
    }

    public ArrayList <ArrayList <Seat>> getSeatingPlan() {
        return this.seatingPlan;
    }

    public void setSeatingPlan(ArrayList <ArrayList <Seat>> seatingPlan) {
        this.seatingPlan = seatingPlan;
    }

    public void setSeatStatus(int venueSlot, Seat seat, boolean assign) {
        ArrayList <Seat> seats = this.seatingPlan.get(venueSlot);

        int index = seats.indexOf(seat);
        this.seatingPlan.get(venueSlot).get(index).setAssignStatus(assign);
    }

    public DateTime getShowingTime() {
        return this.showingTime;
    }

    public void setShowingTime(DateTime showingTime) {
        this.showingTime = showingTime;
    }
}
