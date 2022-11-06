package view;

import handler.InputHandler;
import handler.UIHandler;
import controller.CinemaStaffManager;
import model.*;

public class StaffConfigureMovieView {
    private StaffMovieDetailsView staffMovieDetailsView;
    private Movie movie;
    private String errorMessage;

    public StaffConfigureMovieView(Movie movie) {
        this.movie = movie;
        this.errorMessage = "";
    }

    public void printMenu() {
        MainView.printBoilerPlate("Configure Setting for " + this.movie.getMovieTitle());
        MainView.printMenuContent("""
            Select the detail to be configured.

            1. Movie Title
            2. Movie Type
            3. Age Rating
            4. Showing Status
            5. Cast Member's Names
            6. Movie Director's Name
            7. Movie Synopsis
            8. Movie Duration
            9. Movie Showing Venues
            10. Movie Showing Times
            11. Return.
                """);
    }

    public void appContent(){
        int choice = -1;

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();

            if (choice == 11) {
                this.errorMessage = "";
                return;
            }
            else if (choice <= 11 && choice >= 1 ) {
                this.errorMessage = "";
                CinemaStaffManager.updateExistingMovieDetails(this.movie, choice);
            }
            else {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
        }   while (true);
    }
}