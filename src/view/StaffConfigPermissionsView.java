package view;

import handler.InputHandler;
import controller.CinemaStaffManager;

public class StaffConfigPermissionsView {
    private StaffConfigSettingsView staffConfigSettingsView;

    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure movie goer permissions.
                01. Opt out list by overall ratings
                02. Opt out list by movie sales
                03. Opt in list by overall ratings
                04. Opt in list by movie sales
                05. Back
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
                    System.out.println("Movie goer can no longer view top 5 based on overall ratings.");
                    CinemaStaffManager.optOutOne(1);
                    break;

                case 2:
                    System.out.println("Movie goer can no longer view top 5 based on movie sales.");
                    CinemaStaffManager.optOutOne(2);
                    break;

                case 3:
                    System.out.println("Movie goer can now view top 5 based on overall ratings.");
                    CinemaStaffManager.optInOne(1);
                    break;

                case 4:
                    System.out.println("Movie goer can now view top 5 based on movie sales.");
                    CinemaStaffManager.optInOne(2);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }   while (choice != 5);
    }
}
