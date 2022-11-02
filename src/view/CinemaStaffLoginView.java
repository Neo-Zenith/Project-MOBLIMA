package view;

import controller.CinemaStaffManager;

import model.CinemaStaff;
import handler.InputHandler;

public class CinemaStaffLoginView {
    private CinemaStaff cinemaStaff;
    private MovieAppView movieAppView;

    public CinemaStaffLoginView(CinemaStaff cinemaStaff) {
        this.cinemaStaff = cinemaStaff;
    }
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Welcome to MOBLIMA STAFF."); 
        System.out.println("Please login to access functions.");
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
    
        this.printMenu();
        System.out.println("Please enter your staffID:");
        int staffID = InputHandler.intHandler();
        System.out.println("Please enter the password");
        String password = InputHandler.stringHandler();

        if (CinemaStaffManager.login(username, password) == false){
            System.out.println("Invalid password or email. Try again.");
            System.out.println("Re-enter your staffID");
            staffID = InputHandler.intHandler();
            System.out.println("Re-enter your password");
            password = InputHandler.stringHandler();

            if (CinemaStaffManager.login(staffID, password) == false){
                System.out.println("Wrong login credentials. Exiting Moblima");
        }
        

        this.movieAppView = new MovieAppView();
        this.movieAppView.appContent();

        }
    }
}
