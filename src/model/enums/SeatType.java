package src.model.enums;

/**
 * Enumeration class for the class Seat
 * @author Lee Juin
 * @version 1.0
 */
public enum SeatType {
    
    STANDARD("Standard Seat"),
    COUPLE("Couple Seat");

    private final String display;

    /**
     * Private constructor
     * @param display is the display name to be shown
     */
    private SeatType(String display) {
        this.display = display;
    }

    /**
     * Method to obtain the display name of a enumeration
     * @return The display name of a enumeration
     */
    public String getDisplayName() {
        return this.display;
    }
}
