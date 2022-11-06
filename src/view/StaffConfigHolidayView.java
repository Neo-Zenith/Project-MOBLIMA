package view;

import controller.CinemaStaffManager;
import handler.InputHandler;

public class StaffConfigHolidayView {
    private StaffConfigSettingsView staffConfigSettingsView;

    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure holidays.
                01. Add holiday
                02. Delete holiday
                03. Back
                """);
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            switch (choice) {
                case 1:
                    CinemaStaffManager.configureHoliday(choice);
                    break;

                case 2:
                    CinemaStaffManager.configureHoliday(choice);
                    break;

                case 3:
                    this.staffConfigSettingsView = new StaffConfigSettingsView();
                    this.staffConfigSettingsView.appContent();
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != 3);
    }
}
