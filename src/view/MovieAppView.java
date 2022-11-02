package view;

import database.Database;
import handler.InputHandler;
import model.MovieGoer;

public class MovieAppView extends MainView {

    private MovieGoerView movieGoerView;
    private StaffView staffView;

    public MovieAppView() {}
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Welcome to MOBLIMA!");
        MainView.printBoilerPlate("""
                1. Login.
                2. Register.
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
                    System.out.println("Please enter your username: ");
                    String username = InputHandler.StringHandler();
                    System.out.println("Please enter your password: ");
                    String password = InputHandler.StringHandler();
                    Object user = UserManager.login(username, password);
                    if (user instanceof MovieGoer) {
                        this.movieGoerView = new MovieGoerView();
                        this.movieGoerView.appContent();
                    }
                    else if (user instanceof Staff) {
                        this.staffView = new StaffView();
                        this.staffView.appContent();
                    }
                    else {
                        System.out.println("Error! Invalid username or password! Please try again!");
                    }
                    break;
                case 2:
                    break;
            }
        }   while (choice != 3);
    }
}
