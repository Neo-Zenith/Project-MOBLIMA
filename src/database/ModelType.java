package database;

public enum ModelType {

    CINEPLEX("Cineplex"),
    CINEMA("Cinema"),
    SEAT("Seat"),
    MOVIE_SCHEDULE("MovieSchedule"),
    MOVIE_GOER("MovieGoer"),
    MOVIE("Movie"),
    MOVIE_REVIEW("MovieReview"),
    CINEMA_STAFF("CinemaStaff");

    private final String fileName;

    private ModelType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
