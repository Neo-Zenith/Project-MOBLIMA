package src.handler;

import java.util.Scanner;

/**
 * Generic helper functions for handling inputs
 * 
 * @author Lee Juin
 * @version 1.0
 */
public class InputHandler {
    /**
     * Scanner for reading inputs
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Constructor
     */
    public InputHandler() {
    }

    /**
     * Helper function to read an integer
     * 
     * @return {@code -1} if the input is invalid, otherwise returns the integer
     *         read
     */
    public static int intHandler() {
        try {
            int input = InputHandler.scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Error! Please enter a valid integer!");
            return -1;
        }
    }

    /**
     * Helper function to read a string
     * 
     * @return {@code null} if the input is invalid, otherwise returns the string
     *         read
     */
    public static String stringHandler() {
        try {
            String input = InputHandler.scanner.nextLine();
            return input;
        } catch (Exception e) {
            System.out.println("Error! Please enter a valid string!");
            return null;
        }
    }

    /**
     * Helper function to read a double
     * 
     * @return {@code -1} if the input is invalid, otherwise returns the double read
     */
    public static double doubleHandler() {
        try {
            double input = InputHandler.scanner.nextDouble();
            return input;
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Error! Please enter a valid double!");
            return -1;
        }
    }
}
