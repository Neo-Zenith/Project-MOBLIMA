package src.model.enums;

/**
 * Enumeration class for the Movie Age Rating
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */
public enum MovieAgeRating {
    G("(G) General"),
    PG("(PG) Parental Guidance"),
    PG13("(PG 13) Parental Guidance for under 13 years old"),
    NC16("(NC 16) No children under 16 years old"),
    M18("(M18) Mature content for above 18 years old"),
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
