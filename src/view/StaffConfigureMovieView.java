public class StaffConfigureMovieView {
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
        int failure = 0;
        Database db = new Database();

        do {
            this.printMenu();
            int detail = InputHandler.intHandler();
            failure = CinemaStaffManager.updateExistingMovieDetails(movieNumber, detail);
        }   while (choice != 11 || failure == 0);
    }
}