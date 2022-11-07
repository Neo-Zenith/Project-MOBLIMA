package view;

import controller.*;
import database.*;
import handler.*;


public class DatabaseView extends MainView{
    private StaffAddMovieView staffAddMovieView;
    private String errorMessage;

    public DatabaseView() {
        this.errorMessage = "";
    }
    
    public void printMenu() {
        MainView.printBoilerPlate("Database");
        MainView.printMenuContent("""

                Welcome to the Database! What would you like to do?

                01. Load initial data into database
                02. Add new movies into database
                03. Reset database
                04. Quit and return back
                """);
    }

    public void appContent() {
        int choice = -1;
        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    DatabaseManager.initializePrices();
                    DatabaseManager.initializeCineplexData();
                    DatabaseManager.initializeMovie();
                    DatabaseManager.initializeMovieScheduleData();
                    Database.writeToDatabase();
                    this.errorMessage = "Data loaded successfully!";
                    break;
                case 2:
                    this.staffAddMovieView = new StaffAddMovieView();
                    this.errorMessage = "";
                    staffAddMovieView.appContent();
                    break;
                case 3:
                    Database.resetDatabase();
                    this.errorMessage = "Database resetted successfully!";
                    break;
                case 4:
                    this.errorMessage = "";
                    return;
                default:
                    this.errorMessage = "Error! Please enter a valid input!";
                    continue;
            }
        } while (true);
    }
}
