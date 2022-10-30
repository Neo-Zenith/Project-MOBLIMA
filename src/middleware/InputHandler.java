package middleware;

import java.util.*;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            }
            catch (InputMismatchException e) {
                System.out.println("Error! Please enter a valid integer!");
            }
        }
    }
}
