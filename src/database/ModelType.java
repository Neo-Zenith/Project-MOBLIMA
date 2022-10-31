package database;

public enum ModelType {
    
    CINEPLEX("Cineplex"),
    CINEMA("Cinema"),
    SEAT("Seat"),
    MOVIE_SCHEDULE("MovieSchedule");

    private final String fileName;

    private ModelType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
