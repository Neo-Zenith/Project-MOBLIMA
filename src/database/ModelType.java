package database;

public enum ModelType {

    CINEPLEX("Cineplex"),

    CINEMA("Cinema"),

    MOVIE("Movie"),

    MOVIEGOER("MovieGoer");

    public final String modelName;

    private ModelType(String modelName) {
        this.modelName = modelName;
    }
}
