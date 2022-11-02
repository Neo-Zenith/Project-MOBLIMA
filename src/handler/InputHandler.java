package handler;

import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);
    
    public static int intHandler() {
        try {
            int input = InputHandler.scanner.nextInt();
            scanner.nextLine();
            return input;
        }
        catch (Exception e) {
            System.out.println("Error! Please enter a valid integer!");
            return -1;
        }
    }

    public static String StringHandler() {
        try {
            String input = InputHandler.scanner.nextLine();
            return input;
        }
        catch (Exception e) {
            System.out.println("Error! Please enter valid characters!");
            return null;
        }
    }
}
