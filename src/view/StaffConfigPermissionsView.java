package view;

import handler.InputHandler;
import controller.CinemaStaffManager;

public class StaffConfigPermissionsView {
    private StaffConfigSettingsView staffConfigSettingsView;
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure movie goer permissions.
                1. Opt out list by overall ratings
                2. Opt out list by movie sales
                3. Back
                """);
        System.out.println("====================================");    
    }
    public void appContent(){
        int choice = -1;

        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            CinemaStaffManager.optOutOne(choice);
            switch(choice){
                case 1:
                    System.out.println("Movie goer can no longer view top 5 based on overall ratings.");      
                break;
                
                case 2:
                    System.out.println("Movie goer can no longer view top 5 based on overall ratings.");      
                break;
                
                case 3:
                    this.staffConfigSettingsView = new StaffConfigSettingsView();
                    this.staffConfigSettingsView.appContent();
                break;

                default:
                    System.out.println("Invalid choice");
                break;
            }
        }   while (choice != 3);
    }
}
