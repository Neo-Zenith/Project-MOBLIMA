package view;

import database.Database;
import handler.InputHandler;

public class MovieAppView extends MainView {

    private DatabaseView dbView;
    private CineplexView cpView;

    public MovieAppView() {
        this.dbView = new DatabaseView();
        this.cpView = new CineplexView();
    }
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Welcome to MOBLIMA!");
        MainView.printBoilerPlate("""
                1. Manage Database.
                2. View Cineplexes.
                3. Exit the program.
                """);
    }

    public void appContent() {
        int choice = -1;
        Database db = new Database();

        do {
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    this.dbView.appContent();
                    break;
                case 2:
                    this.cpView.appContent();
                    break;
            }
        }   while (choice != 3);
    }
}
