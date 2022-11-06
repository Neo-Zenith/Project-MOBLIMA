package view;

import handler.InputHandler;

import model.enums.CinemaClass;
import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.Seat;
import model.DateTime;
import model.Cineplex;
import model.Cinema;

import controller.CinemaManager;
import database.*;
import java.util.*;

import controller.CinemaStaffManager;

public class StaffAddMovieView {
    private DatabaseView databaseView;

    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
            Adding new movies...
                """);
        }

    public void appContent(){
        this.printMenu();
        this.addNewMovie();

        this.databaseView = new DatabaseView();
        this.databaseView.appContent();
    }


    public void addNewMovie(){

        System.out.println("Enter the name of new movie");
        String title = InputHandler.stringHandler();

        System.out.println("Enter the age rating for " + title);
        System.out.println("1. R");
        System.out.println("2. G");
        System.out.println("3. PG");
        System.out.println("4. PG13");
        System.out.println("5. NC16");
        System.out.println("6. M18");
        System.out.println("7. R21");
        int choice = InputHandler.intHandler();
        if (choice < 1 || choice > 7){
            System.out.println("Invalid age rating");
            return;
        }
        MovieAgeRating movieAgeRating = MovieAgeRating.values()[choice - 1];

        System.out.println("Enter the showing status for " + title);
        System.out.println("1. COMING_SOON");
        System.out.println("2.  PREVIEW");
        System.out.println("3. NOW_SHOWING");
        choice = InputHandler.intHandler();
        if (choice < 1 || choice > 3){
            System.out.println("Invalid showing status");
            return;
        }
        MovieShowingStatus movieShowingStatus = MovieShowingStatus.values()[choice-1];
        

        ArrayList <String> movieCast = new ArrayList<>();
        System.out.println("Enter the number of movie cast/casts for " + title);
        int numberOfCasts = InputHandler.intHandler(); 
        if (numberOfCasts == 0){
            System.out.println("A movie cannot have no casts");
            return;
        } else if (numberOfCasts == 1){
            System.out.println("The number of movie casts cannot be negative.");
            return;
        }
        for (int i = 0; i < numberOfCasts; i++){
            System.out.println("Enter the name of cast " + (i + 1));
            String castName = InputHandler.stringHandler();
            movieCast.add(castName);
        }
        
        System.out.println("Enter the name of the director for " + title);
        String director = InputHandler.stringHandler();
  
        System.out.println("Enter the name of synopsis for " + title);
        String synopsis = InputHandler.stringHandler();
        
        System.out.println("Enter duration of " + title);
        double duration = InputHandler.doubleHandler();
        if (duration < 0){
            System.out.println("Duration cannot be negative");
            return;
        }

        System.out.println("Enter the movie type of " + title);
        System.out.println("1. Standard Movie");
        System.out.println("2. Blockbuster Movie");
        System.out.println("3. 3D Movie");
        int movieTypeChoice = InputHandler.intHandler();

        ArrayList <Cinema> showingVenue;
        ArrayList <ArrayList <Seat>> seatingPlan = new ArrayList<ArrayList<Seat>>();
        ArrayList <DateTime> showingTime = new ArrayList<DateTime>();
        CinemaClass cinemaClass;
        Cineplex cineplex;
        ArrayList <Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());
        Collections.sort(cineplexes);

        System.out.println("Enter the cinema class for this movie");
        System.out.println("1. STANDARD");
        System.out.println("2. PLATINUM");
        System.out.println("3. IMAX");
        choice = InputHandler.intHandler();
        if (choice < 1 || choice > 3){
            System.out.println("Invalid cinema class");
            return;
        }
        cinemaClass = CinemaClass.values()[choice-1];
        
        System.out.println("Enter which cineplex is to be added");
        for (int i = 0; i < cineplexes.size(); i++){
            System.out.println((i+1) +" Cineplex Name: " + cineplexes.get(i).getCineplexName() + " Cineplex Location: " + cineplexes.get(i).getCineplexLocation());
        }
        choice = InputHandler.intHandler();
        if (choice < 1 || choice > cineplexes.size()){
            System.out.println("Invalid cineplex");
            return;
        }
        cineplex = cineplexes.get(choice - 1);

        showingVenue = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
        }

        System.out.println("Enter the number of showing times for " + title);
        int numOfShowingTimes =InputHandler.intHandler();
        if (numOfShowingTimes == 0){
            System.out.println("A movie cannot have no showing times");
            return;
        }
        else if (numOfShowingTimes < 0){
            System.out.println("Invalid number of showing times");
            return;
        }
        for (int i = 0; i < numOfShowingTimes; i++){
            DateTime time = CinemaStaffManager.queryDate();
            showingTime.add(time);
        }        
        
       CinemaStaffManager.movieAdder(title, movieAgeRating, movieShowingStatus, movieCast, director,
                                    synopsis, duration, movieTypeChoice, showingVenue, seatingPlan, showingTime);
    }

    
}
