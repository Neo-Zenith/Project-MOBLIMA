package view;

import handler.InputHandler;
import controller.CinemaStaffManager;

public class StaffConfigPriceView {
    private StaffConfigSettingsView staffConfigSettingsView;

    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                Select the pricing to be configured
                01. Movie Type
                02. Cinema Type
                03. User Age Group
                04. Seat
                05. Special Dates (Weekends/Holidays)
                06. Back
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
                    System.out.println("====================================");
                    System.out.println("How would you like to configure MovieType pricings?");
                    System.out.println("1. Configure Standard Movie Price");
                    System.out.println("2. Configure BlockbusterMoviePrice");
                    System.out.println("3. Configure 3D Movie Price");
                    System.out.println("====================================");
                    int movieTypeChoice = InputHandler.intHandler();
                    if (movieTypeChoice == 1) {
                        CinemaStaffManager.configurePrice(7);
                    } else if (movieTypeChoice == 2) {
                        CinemaStaffManager.configurePrice(5);
                    } else if (movieTypeChoice == 3) {
                        CinemaStaffManager.configurePrice(6);
                    } else {
                        System.out.println("Invalid choice");
                        this.appContent();
                    }
                    break;
                case 2:
                    System.out.println("====================================");
                    System.out.println("How would you like to configure Cinema pricings?");
                    System.out.println("1. Configure Standard Cinema Price");
                    System.out.println("2. Configure Imax Cinema Price");
                    System.out.println("3. Configure Platinum Cinema Price");
                    System.out.println("====================================");
                    int cinemaChoice = InputHandler.intHandler();
                    if (cinemaChoice == 1) {
                        CinemaStaffManager.configurePrice(1);
                    } else if (cinemaChoice == 2) {
                        CinemaStaffManager.configurePrice(3);
                    } else if (cinemaChoice == 3) {
                        CinemaStaffManager.configurePrice(2);
                    } else {
                        System.out.println("Invalid choice");
                        this.appContent();
                    }
                    break;
                case 3:
                    System.out.println("====================================");
                    System.out.println("How would you like to configure Age Group pricings?");
                    System.out.println("1. Configure Child price");
                    System.out.println("2. Configure Student Price");
                    System.out.println("3. Configure Adult Price");
                    System.out.println("4. Configure Senior Citizen Price");
                    System.out.println("====================================");
                    int ageGroupChoice = InputHandler.intHandler();
                    if (ageGroupChoice == 1) {
                        CinemaStaffManager.configurePrice(8);
                    } else if (ageGroupChoice == 2) {
                        CinemaStaffManager.configurePrice(9);
                    } else if (ageGroupChoice == 3) {
                        CinemaStaffManager.configurePrice(10);
                    } else if (ageGroupChoice == 4) {
                        CinemaStaffManager.configurePrice(11);
                    } else {
                        System.out.println("Invalid choice");
                        this.appContent();
                    }

                    break;
                case 4:
                    CinemaStaffManager.configurePrice(4);
                    break;
                case 5:
                    System.out.println("====================================");
                    System.out.println("How would you like to configure Special Dates pricings?");
                    System.out.println("1. Holiday Price");
                    System.out.println("2. Weekend Price");
                    System.out.println("====================================");
                    int specialDatesChoice = InputHandler.intHandler();
                    if (specialDatesChoice == 1) {
                        CinemaStaffManager.configurePrice(12);
                    } else if (specialDatesChoice == 2) {
                        CinemaStaffManager.configurePrice(13);
                    } else {
                        System.out.println("Invalid choice");
                        this.appContent();
                    }

                    break;
                case 6:
                    this.staffConfigSettingsView = new StaffConfigSettingsView();
                    this.staffConfigSettingsView.appContent();
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != 6);
    }
}
