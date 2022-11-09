package src.database;

/**
 * Enumeration class for handling conversion from database file to HashMap
 * @author Lee Juin
 * @version 1.0
 */
public enum ModelType {
    
    /**
     * All the models presented in the application and their respective file name
     */
    CINEPLEX("Cineplex"),
    CINEMA("Cinema"),
    SEAT("Seat"),
    MOVIE_SCHEDULE("MovieSchedule"),
    BOOKING_HISTORY("BookingHistory"),
    MOVIE_GOER("MovieGoer"),
    MOVIE("Movie"),
    MOVIE_REVIEW("MovieReview"),
    CINEMA_STAFF("CinemaStaff"),
    HOLIDAY("Holiday"),
    PRICES("Prices"),
    MOVIE_TICKET("MovieTicket"),
    PAYMENT("Payment"),
    PERMISSION("Permission");

    /**
     * File name of the model
     */
    private final String fileName;

    /**
     * Private constructor
     * @param fileName is the file name to be used for the model
     */
    private ModelType(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the file name of the model
     * @return The file name of the model
     */
    public String getFileName() {
        return this.fileName;
    }
}
