package src.model.enums;

/**
 * Enumeration class for the Movie Type
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */

public enum MovieType {
    /**
     * Standard movie
     */
    Standard("Standard"),

    /**
     * Blockbuster movie
     */
    Blockbuster("Blockbuster"),

    /**
     * 3D movie
     */
    ThreeD("3D");

    /**
     * The name of the enum class
     */
    private final String display;

    /**
     * Private constructor
     * 
     * @param display is the display name to be shown
     */
    private MovieType(String display) {
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