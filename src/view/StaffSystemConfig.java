package view;

import handler.*;

public class StaffSystemConfig extends MainView {
    private StaffMovieDetailsView staffMovieDetailsView;
    private StaffConfigSettingsView staffConfigSettingsView;
    private StaffMovieListRankingView staffMovieListRankingView;
    private DatabaseView databaseView;
    private String errorMessage;

    public StaffSystemConfig() {
        this.errorMessage = "";
    }
    
    public void printMenu() {
        MainView.printBoilerPlate("Staff Module");
        MainView.printMenuContent("""
                Select a function to be executed.

                1. Configure movie details
                2. Configure system settings
                3. Configure Database
                4. List Top 5
                5. Logout
                """);
        }
    
    public void appContent(){
        int choice = -1;

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();
            
            if (choice < 0 || choice > 5){
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            switch(choice) {
                case 1:
                    this.errorMessage = "";
                    this.staffMovieDetailsView = new StaffMovieDetailsView();
                    this.staffMovieDetailsView.appContent();
                    break;

                case 2:
                    this.errorMessage = "";
                    this.staffConfigSettingsView = new StaffConfigSettingsView();
                    this.staffConfigSettingsView.appContent();
                    break;

                case 3:
                    this.errorMessage = "";
                    this.databaseView = new DatabaseView();
                    this.databaseView.appContent();
                    break;

                case 4:
                    this.errorMessage = "";
                    this.staffMovieListRankingView = new StaffMovieListRankingView();
                    this.staffMovieListRankingView.appContent();
                    break;

                case 5:
                    this.errorMessage = "";
                    return;
            }
        }   while (true);
    }
}
