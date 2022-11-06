package view;

import controller.DatabaseManager;
import database.Database;
import handler.InputHandler;

public class DatabaseView extends MainView{
    private StaffAddMovieView staffAddMovieView;
    public DatabaseView() {}
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Welcome to the Database!");
        MainView.printBoilerPlate("""
                01. Load initial data into database.
                02. Add new movies into database.
                03. Reset database.
                04. Return back.
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
                    DatabaseManager.initializeMovie();
                    DatabaseManager.initializeMovieScheduleData();
                    Database.writeToDatabase();
                    break;
                case 2:
                    this.staffAddMovieView = new StaffAddMovieView();
                    staffAddMovieView.appContent();
                    break;
                case 3:
                    Database.resetDatabase();
                    System.out.println("Database resetted successfully!");
                    break;
                case 4:
                    break;
            }
        } while (choice != 4);
    }
}
