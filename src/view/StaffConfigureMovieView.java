package view;

import handler.InputHandler;
import controller.CinemaStaffManager;

public class StaffConfigureMovieView {
    private StaffMovieDetailsView staffMovieDetailsView;
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
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
            11. Back
                """);
        System.out.println("====================================");
    }
    public void appContent(int movieNumber){
        int choice = -1;

        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            if (choice == 11){
                this.staffMovieDetailsView = new StaffMovieDetailsView();
                staffMovieDetailsView.appContent();
            }
            else if (choice <= 11 && choice >= 1 ) {
                CinemaStaffManager.updateExistingMovieDetails(movieNumber, choice);
            }
            else {
                System.out.println("Invalid choice");
            }
        }   while (choice != 11);
    }
}