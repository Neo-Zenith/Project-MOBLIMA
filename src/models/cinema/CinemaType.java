package models.cinema;

public enum CinemaType {
    PLATINUM,
    STANDARD;

    public static int getNumCinemaType() {
        return CinemaType.values().length;
    }
}
