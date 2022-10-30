package view;

import middleware.*;
import database.*;

public class DatabaseView extends MainView{
    
    public void printMenuScreen() {
        System.out.println("======================");
        System.out.println("Welcome to the Database. What would you want to do?");
        System.out.println("1. Load Initial Data into Database");
        System.out.println("2. Reset Database.");
        System.out.println("3. Return back.");
        System.out.println("======================");
    }

    public void appView() {
        int choice = -1;

        do {
            printMenuScreen();
            choice = InputHandler.readInt();

            switch (choice) {
                case 1:  
                    UIHandler.clearScreen();   
                    if (Database.loadInitialCineplexData()) {
                        System.out.println("Initial data loaded successfully!");
                    }
                    break;
                case 2: 
                    UIHandler.clearScreen();            
                    if (Database.resetDatabase()) {
                        System.out.println("Database resetted successfully!");
                    }
                    break;
                default:
                    break;
            }
        }   while (choice != 3);
    }
}
