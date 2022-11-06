package view;

import handler.InputHandler;
import handler.UIHandler;
import model.MovieGoer;
import controller.CinemaStaffManager;

public class StaffConfigPermissionsView {
    private String errorMessage;

    public StaffConfigPermissionsView() {
        this.errorMessage = "";
    }

    public void printMenu() {
        MainView.printBoilerPlate("Configure User Permissions");
        MainView.printMenuContent("""
                How would you like to configure movie goer permissions.

                01. Opt out list by overall ratings permission
                02. Opt out list by movie sales permission
                03. Opt in list by overall ratings permission
                04. Opt in list by movie sales permission
                05. Return
                """);
    }

    public void appContent() {
        int choice = -1;

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    this.errorMessage = "";
                    System.out.println("Movie goer can no longer view top 5 based on overall ratings.");
                    CinemaStaffManager.optInOut(1, false);
                    break;

                case 2:
                    this.errorMessage = "";
                    System.out.println("Movie goer can no longer view top 5 based on movie sales.");
                    CinemaStaffManager.optInOut(2, false);
                    break;

                case 3:
                    this.errorMessage = "";
                    System.out.println("Movie goer can now view top 5 based on overall ratings.");
                    CinemaStaffManager.optInOut(1, true);
                    break;

                case 4:
                    this.errorMessage = "";
                    System.out.println("Movie goer can now view top 5 based on movie sales.");
                    CinemaStaffManager.optInOut(2, true);
                    break;

                case 5:
                    this.errorMessage = "";
                    return;

                default:
                    this.errorMessage = "Error! Please enter a valid input!";
                    continue;
            }
        }   while (true);
    }
}
