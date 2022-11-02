package database;

public enum ModelType {
    
    CINEPLEX("Cineplex"),
    CINEMA("Cinema"),
    SEAT("Seat"),
    MOVIE_SCHEDULE("MovieSchedule"),
    BOOKING_HISTORY("BookingHistory"),
    MOVIE_GOER("MovieGoer"),
    MOVIE("Movie"),
    MOVIE_REVIEW("MovieReview"),
    PAYMENT("Payment"),
    MOVIE_TICKET("MovieTicket");

    private final String fileName;

    private ModelType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
