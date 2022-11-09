package src.model.enums;

/**
 * Enumeration class for the Showing Status
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */

public enum MovieShowingStatus {
    COMING_SOON("Coming Soon"),
    PREVIEW("Preview"),
    NOW_SHOWING("Now Showing"),
    END_OF_SHOWING("End of Showing");

    private final String display;

    /**
     * Private constructor
     * 
     * @param display is the display name to be shown
     */
    private MovieShowingStatus(String display) {
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