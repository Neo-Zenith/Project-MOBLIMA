package handler;

import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);
    
    public static int intHandler() {
        try {
            int input = InputHandler.scanner.nextInt();
            return input;
        }
        catch (Exception e) {
            System.out.println("Error! Please enter a valid integer!");
            return -1;
        }
    }
}
