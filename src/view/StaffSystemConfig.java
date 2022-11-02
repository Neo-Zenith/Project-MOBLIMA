package view;

import database.Database;
import handler.InputHandler;

public class StaffSystemConfig extends MainView{
    private StaffMovieDetailsView staffMovieDetailsView;
    private StaffConfigSettingsView staffConfigSettingsView;
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("System Configuration Module");
        MainView.printBoilerPlate("""
                Select what is to be configured
                1. Configure movie details
                2. Configure system settings
                3. List Top 5
                4. Logout
                """);
        }
    
    public void appContent(){
        int choice = -1;

        do {
            this.printMenu();
            choice = InputHandler.intHandler();

            switch(choice){
                case 1:
                    this.staffMovieDetailsView = new StaffMovieDetailsView();
                    this.staffMovieDetailsView.appContent();
                case 2:
                    this.staffConfigSettingsView = new StaffConfigSettingsView();
                    this.staffConfigSettingsView.appContent();
                case 3:
                    /*
                     * List Top 5
                     */    
            }
        }   while (choice != 4);
    }
}
