package model;

public class MovieTicket {

    private String UUID;
    private Movie movieToWatch;
    private DateTime showTime;
    private Seat bookedSeat;
    private Cinema showingVenue;

    public MovieTicket(String UUID, Movie movieToWatch, DateTime showTime, Seat bookedSeat, Cinema cinema) {
        this.setUUID(UUID);
        this.setMovieToWatch(movieToWatch);
        this.setShowTime(showTime);
        this.setBookedSeat(bookedSeat);
        this.setShowingVenue(cinema);
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Movie getMovieToWatch() {
        return this.movieToWatch;
    }

    public void setMovieToWatch(Movie movieToWatch) {
        this.movieToWatch = movieToWatch;
    }

    public DateTime getShowTime() {
        return this.showTime;
    }

    public void setShowTime(DateTime showTime) {
        this.showTime = showTime;
    }

    public Seat getBookedSeat() {
        return this.bookedSeat;
    }

    public void setBookedSeat(Seat bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public Cinema getShowingVenue() {
        return this.showingVenue;
    }

    public void setShowingVenue(Cinema showingVenue) {
        this.showingVenue = showingVenue;
    }
}
