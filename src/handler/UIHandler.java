package src.handler;

/**
 * Generic helper functions for handling UI experience
 * @author Lee Juin
 * @version 1.0
 */
public class UIHandler {

    /**
     * Constructor
     */
    public UIHandler() {}

    /**
     * Method to clear the old content screen for better UI experiences
     */
    public static void clearScreen() {
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 
        catch (Exception err) {

        }
    }
}
