package src.model.enums;

/**
 * Enumeration class for Cinema
 * 
 * @author Lee Juin
 * @version 1.0
 */
public enum CinemaClass {

    /**
     * Standard cinema
     */
    STANDARD("Standard Cinema"),

    /**
     * Platinum cinema
     */
    PLATINUM("Platinum Movie Suite"),

    /**
     * IMAX cinema
     */
    IMAX("IMAX Cinema Experience");

    private final String display;

    /**
     * Private constructor
     * 
     * @param display is the display name to be shown
     */
    private CinemaClass(String display) {
        this.display = display;
    }

    /**
     * Method to obtain the display name of a enumeration
     * 
     * @return The display name of a enumeration
     */
    public String getDisplayName() {
        return this.display;
    }
}
