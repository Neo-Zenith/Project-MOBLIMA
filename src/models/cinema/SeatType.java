package models;

public enum SeatType {
    STANDARD,
    COUPLE;

    public static int getNumSeatType() {
        return SeatType.values().length;
    }
}
