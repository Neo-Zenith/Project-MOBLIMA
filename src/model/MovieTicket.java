package model;

public class MovieTicket {

    private String UUID;
    private Movie movieToWatch;
    private DateTime showTime;
    private Seat bookedSeat;

    public MovieTicket(String UUID, Movie movieToWatch, DateTime showTime, Seat bookedSeat) {
        this.setUUID(UUID);
        this.setMovieToWatch(movieToWatch);
        this.setShowTime(showTime);
        this.setBookedSeat(bookedSeat);
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
}
