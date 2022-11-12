package src.model.enums;

/**
 * Enumeration class for the Showing Status
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */

public enum MovieShowingStatus {

    /**
     * Coming soon movie status
     */
    COMING_SOON("Coming Soon"),

    /**
     * Preview movie status
     */
    PREVIEW("Preview"),

    /**
     * Now Showing movie status
     */
    NOW_SHOWING("Now Showing"),

    /**
     * End of showing movie status
     */
    END_OF_SHOWING("End of Showing");

    /**
     * The name of the enum class
     */
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