package view;

import controller.DatabaseManager;
import database.Database;
import handler.InputHandler;

public class DatabaseView extends MainView{
    
    public DatabaseView() {}
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Welcome to the Database!");
        MainView.printBoilerPlate("""
                1. Load initial data into database.
                2. Reset database.
                3. Return back.
                """);
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        do {
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    DatabaseManager.initializePrices();
                    DatabaseManager.initializeCineplexData();
                    System.out.println("Cineplex data initialized successfully!");
                    DatabaseManager.initializeMovie();
                    System.out.println("Movie data initialized successfully!");
                    DatabaseManager.initializeMovieScheduleData(); 
                    System.out.println("Movie schedule data initialized successfully!");
                    Database.writeToDatabase();
                    break;
                case 2:                   
                    Database.resetDatabase();
                    System.out.println("Database resetted successfully!");
                    break;
                case 3:
                    break;
            }
        } while (choice != 3);
    }
}
