package view;

import handler.InputHandler;

public class MovieAppView extends MainView {

    private DatabaseView dbView;

    public MovieAppView() {
        this.dbView = new DatabaseView();
    }
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Welcome to MOBLIMA!");
        MainView.printBoilerPlate("""
                1. Manage Database.
                2. Exit the program.
                """);
    }

    public void appContent() {
        int choice = -1;
        do {
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    this.dbView.appContent();
                    break;
                case 2:
                    break;
            }
        }   while (choice != 2);
    }
}
