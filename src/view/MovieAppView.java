package view;

import database.Database;
import handler.InputHandler;
import model.MovieGoer;
import model.CinemaStaff;
import controller.UserManager;

public class MovieAppView extends MainView {

    private MovieGoerView movieGoerView;
    private StaffSystemConfig staffView;

    public MovieAppView() {
    }

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
        String username;
        String password;

        do {
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    System.out.println("Please enter your username: ");
                    username = InputHandler.stringHandler();
                    System.out.println("Please enter your password: ");
                    password = InputHandler.stringHandler();
                    Object user = UserManager.login(username, password);
                    if (user instanceof MovieGoer) {
                        MovieMenuView menu = new MovieMenuView();
                        menu.appContent();
                    } else if (user instanceof CinemaStaff) {
                        this.staffView = new StaffSystemConfig();
                        this.staffView.appContent();
                    } else {
                        System.out.println("Error! Invalid username or password! Please try again!");
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("Please enter a unique username");
                        username = InputHandler.stringHandler();
                        System.out.println("Please enter a password");
                        password = InputHandler.stringHandler();
                        if (UserManager.checkUniqueUser(username)) {
                            break;
                        }
                    }
                    break;
            }
        } while (choice != 3);
    }
}
