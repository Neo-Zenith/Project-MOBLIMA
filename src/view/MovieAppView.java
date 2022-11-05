package view;

import database.Database;
import handler.InputHandler;
import model.MovieGoer;
import model.enums.MovieGoerAge;
import model.CinemaStaff;
import controller.DatabaseManager;
import controller.MovieGoerManager;
import controller.UserManager;

public class MovieAppView extends MainView {
    private StaffSystemConfig staffView;

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


    public void printAgeGroup() {
        System.out.println("====================================");
        System.out.println("What is your age group?");
        MainView.printBoilerPlate("""
                1. Adult.
                2. Child.
                3. Senior Citizen.
                4. Student.
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
                        MovieGoer movieGoer = (MovieGoer) user;
                        MovieMenuView menu = new MovieMenuView(movieGoer);
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
                        System.out.println("Username has been taken!");
                    }
                    
                    int choice1 = -1;
                    MovieGoerAge movieGoerAge = MovieGoerAge.Adult;
                    this.printAgeGroup();
                    choice1 = InputHandler.intHandler();
                    switch (choice1) {
                        case 1:
                            movieGoerAge = MovieGoerAge.Adult;
                            break;
                        case 2:
                            movieGoerAge = MovieGoerAge.Child;
                            break;
                        case 3:
                            movieGoerAge = MovieGoerAge.SeniorCitizen;
                            break;
                        case 4:
                            movieGoerAge = MovieGoerAge.Student;
                            break;
                    }

                    System.out.println("Enter your name: ");
                    String name = InputHandler.stringHandler();
                    System.out.println("Enter your email: ");
                    String email = InputHandler.stringHandler();
                    System.out.println("Enter your mobile number: ");
                    String mobileNum = InputHandler.stringHandler();

                    MovieGoer movieGoer = UserManager.register(movieGoerAge, name, username, password, email, mobileNum);
                    MovieMenuView menu = new MovieMenuView(movieGoer);
                    menu.appContent();
                    break; 
            }
        } while (choice != 3);
    }
}
