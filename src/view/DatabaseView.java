package view;

import controller.DatabaseManager;
import database.Database;
import handler.InputHandler;

public class DatabaseView extends MainView {

    public DatabaseView() {
    }

    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Welcome to the Database!");
        MainView.printBoilerPlate("""
                1. Load Cineplex and Cinema data into database.
                2. Load Movie Schedule data into database.
                3. Load Movie into database
                4. Load MovieGoer into database
                5. Load MovieReview into database
                6. Reset database.
                7. Return back.
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
                    DatabaseManager.initializeCineplexData();
                    Database.writeToDatabase();
                    break;
                case 2:
                    DatabaseManager.initializeMovieScheduleData();
                    Database.writeToDatabase();
                    break;
                case 3:
                    DatabaseManager.resetDatabase();
                    break;
                case 4:
                    break;
            }
        } while (choice != 4);
    }
}
