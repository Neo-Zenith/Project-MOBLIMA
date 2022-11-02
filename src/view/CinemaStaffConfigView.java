package view;

import java.util.ArrayList;


import controller.CinemaStaffManager;
import controller.CinemaManager;
import controller.MovieScheduleManager;

import model.MovieSchedule;
import model.CinemaStaff;
import model.Cinema;
import model.Cineplex;
import handler.InputHandler;


public class CinemaStaffConfigView {
    private ArrayList <MovieSchedule> movieSchedules;
    private CinemaStaff cinemaStaff;
    private Cinema cinema;

    
    
    public void printMainMenu() {
        System.out.println("====================================");
        System.out.println("System Configuration Module");
        MainView.printBoilerPlate("""
                Select what is to be configured
                1. Configure movie details
                2. Configure system settings
                3. Logout
                """);
        System.out.println("====================================");
    }

    public void printMovieTitleConfigMenu(){
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                Select the movie to be configured    
                """);
        this.movieSchedules = MovieScheduleManager.printMovieSchedule(this.cinema);
        System.out.println("====================================");
    
    }

    public void printMovieDetailMenu(){
        System.out.println("====================================");
        MainView.printBoilerPlate("""
            Select the details to be configured
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
                """);
        System.out.println("====================================");
    }
   

    public void printSystemDisplayMenu(){
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                Select the system settings to be configured
                1. Configure pricings.
                2. Configure holidays.    
                """);
        System.out.println("====================================");    
    }

    public void printPricingConfigMenu(){
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure pricings.
                1. Movie Type
                2. Cinema Type
                3. User Age Group
                4. Seat Type
                5. Special Dates (Weekends/Holidays)     
                """);
        System.out.println("====================================");    
    }

    public void printHolidayConfigMenu(){
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure holidays.
                1. Add holiday
                2. Delete holiday
                """);
        System.out.println("====================================");    
       
    }



    public void appContent() {
        int choice = -1;
        
        do {
            this.printMainMenu();
            choice = InputHandler.intHandler();
            switch (choice){
                case 1:
                this.printMovieTitleConfigMenu();
                int movieNumber = InputHandler.intHandler();
                this.printMovieDetailMenu();
                int detail = InputHandler.intHandler();
                if (CinemaStaffManager.updateExistingMovieDetails(movieNumber, detail) == 1){
                    break;
                };
                break;

                case 2:
                this.printSystemDisplayMenu();
                int setting = InputHandler.intHandler();
                if (setting == 1){ 
                    this.printPricingConfigMenu();
                    int pricingChoice = InputHandler.intHandler();
                    if(CinemaStaffManager.configurePrice(pricingChoice) == 1){
                        break;
                    };
                }
                else if (setting == 2){ 
                    this.printHolidayConfigMenu();
                    int holidayChoice = InputHandler.intHandler();
                    if(CinemaStaffManager.configureHoliday(holidayChoice) == 1){
                        break;
                    };
                }
                break;
   
                default:
                break;

            }
        }
        
        while(choice > 0  &&  choice < 2);

        // this.movieAppView = new MovieAppView();
        // this.movieAppView.appContent();

    }
}

