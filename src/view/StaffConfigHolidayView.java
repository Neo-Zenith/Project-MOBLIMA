package view;

import controller.CinemaStaffManager;
import handler.InputHandler;

public class StaffConfigHolidayView {
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure holidays.
                1. Add holiday
                2. Delete holiday
                3. Back
                """);
        System.out.println("====================================");    
    }
    public void appContent(){
        int choice = -1;

        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            
            CinemaStaffManager.configureHoliday(choice);
        }   while (choice != 3);
    }
}
