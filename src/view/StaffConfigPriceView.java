package view;

import handler.InputHandler;
import handler.UIHandler;
import controller.CinemaStaffManager;

public class StaffConfigPriceView {
    private String errorMessage;

    public StaffConfigPriceView() {
        this.errorMessage = "";
    }

    public void printMenu() {
        MainView.printBoilerPlate("Price Configuration");
        MainView.printMenuContent("""

                Select the pricing to be configured

                01. Movie Type
                02. Cinema Class
                03. User Age Group
                04. Seat
                05. Special Dates (Weekends/Holidays)
                06. Back
                """);
    }

    public void printMovieTypePrice() {
        MainView.printBoilerPlate("Configure Movie Type Pricing");
        MainView.printMenuContent("""
            How would you like to configure Movie Type pricings?

            1. Configure Standard Movie Price
            2. Configure BlockbusterMoviePrice
            3. Configure 3D Movie Price
                """);
    }

    public void printCinemaClassPrice() {
        MainView.printBoilerPlate("Configure Cinema Class Pricing");
        MainView.printMenuContent("""
            How would you like to configure Cinema Class pricings?

            1. Configure Standard Cinema Price
            2. Configure IMAX Cinema Price
            3. Configure Platinum Cinema Price
                """);
    }

    public void printAgeGroupPrice() {
        MainView.printBoilerPlate("Configure Age Group Pricing");
        MainView.printMenuContent("""
            How would you like to configure Age Group pricings?

            1. Configure Child Ticket Price
            2. Configure Student Ticket Price
            3. Configure Adult Ticket Price
            4. Configure Senior Citizen Ticket Price
                """);
    }

    public void printSpecialDatePrice() {
        MainView.printBoilerPlate("Configure Special Dates Pricing");
        MainView.printMenuContent("""
            How would you like to configure Special Dates pricings?

            1. Configure Holiday Ticket Price
            2. Configure Weekend Ticket Price
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
                    do {
                        UIHandler.clearScreen();
                        System.out.println(this.errorMessage);
                        this.printMovieTypePrice();
                        int movieTypeChoice = InputHandler.intHandler();
                        if (movieTypeChoice == 1) {
                            CinemaStaffManager.configurePrice(7);
                            break;
                        } 
                        else if (movieTypeChoice == 2) {
                            CinemaStaffManager.configurePrice(5);
                            break;
                        } 
                        else if (movieTypeChoice == 3) {
                            CinemaStaffManager.configurePrice(6);
                            break;
                        } 
                        else {
                            this.errorMessage = "Error! Please enter a valid input!";
                            continue;
                        }
                    }   while (true);
                    break;
                
                case 2:
                    do {
                        UIHandler.clearScreen();
                        System.out.println(this.errorMessage);
                        this.printCinemaClassPrice();
                        int cinemaChoice = InputHandler.intHandler();
                        if (cinemaChoice == 1) {
                            CinemaStaffManager.configurePrice(1);
                            break;
                        } else if (cinemaChoice == 2) {
                            CinemaStaffManager.configurePrice(3);
                            break;
                        } else if (cinemaChoice == 3) {
                            CinemaStaffManager.configurePrice(2);
                            break;
                        } else {
                            this.errorMessage = "Error! Please enter a valid input!";
                            continue;
                        }
                    }   while (true);
                    break;
                    
                    
                case 3:
                    do {
                        UIHandler.clearScreen();
                        System.out.println(this.errorMessage);
                        this.printAgeGroupPrice();
                        int ageChoice = InputHandler.intHandler();
                        if (ageChoice == 1) {
                            CinemaStaffManager.configurePrice(8);
                            break;
                        } else if (ageChoice == 2) {
                            CinemaStaffManager.configurePrice(9);
                            break;
                        } else if (ageChoice == 3) {
                            CinemaStaffManager.configurePrice(10);
                            break;
                        } else if (ageChoice == 4) {
                            CinemaStaffManager.configurePrice(11);
                            break;
                        } else {
                            this.errorMessage = "Error! Please enter a valid input!";
                            continue;
                        }
                    }   while (true);
                    break;

                case 4:
                    CinemaStaffManager.configurePrice(4);
                    break;

                case 5:
                    do {
                        UIHandler.clearScreen();
                        System.out.println(this.errorMessage);
                        this.printSpecialDatePrice();
                        int specialDatePrice = InputHandler.intHandler();
                        if (specialDatePrice == 1) {
                            CinemaStaffManager.configurePrice(12);
                            break;
                        } else if (specialDatePrice == 2) {
                            CinemaStaffManager.configurePrice(13);
                            break; 
                        } else {
                            this.errorMessage = "Error! Please enter a valid input!";
                            continue;
                        }
                    }   while (true);
                    break;

                case 6:
                    this.errorMessage = "";
                    return;
                default:
                    this.errorMessage = "Error! Please enter a valid input!";
                    break;
            }
        } while (choice != 6);
    }
}
