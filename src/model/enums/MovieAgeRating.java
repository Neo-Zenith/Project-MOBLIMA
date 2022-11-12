package src.model.enums;

/**
 * Enumeration class for the Movie Age Rating
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */
public enum MovieAgeRating {
    /**
     * General rating
     */
    G("(G) General"),

    /**
     * Parental guidance
     */
    PG("(PG) Parental Guidance"),

    /**
     * Parental guidance under 13
     */
    PG13("(PG 13) Parental Guidance for under 13 years old"),

    /**
     * No children under 16
     */
    NC16("(NC 16) No children under 16 years old"),

    /**
     * Mature content for 18 above
     */
    M18("(M18) Mature content for above 18 years old"),

    /**
     * Restricted for 21 and above
     */
    R21("(R21) Restricted for above 21 years old");

    private final String display;

    /**
     * Private constructor
     * 
     * @param display is the display name to be shown
     */
    private MovieAgeRating(String display) {
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
