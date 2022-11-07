package view;

import handler.*;
import model.enums.*;
import model.*;
import controller.*;
import database.*;
import java.util.*;


public class StaffAddMovieView {
    private String errorMessage;

    public StaffAddMovieView() {
        this.errorMessage = "";
    }

    public void printMenu() {
        MainView.printBoilerPlate("Add New Movies");
    }

    public void printAgeRating() {
        MainView.printMenuContent("""
            Enter the Age Rating

            1. R
            2. G
            3. PG13
            4. PG13
            5. NC16
            6. M18
            7. R21
                """);
    }

    public void printShowingStatus() {
        MainView.printMenuContent("""
            Enter the showing status

            1. Coming Soon
            2. Preview
            3. Now Showing
        """);
    }

    public void printMovieType() {
        MainView.printMenuContent("""
            Enter the movie type

            1. Standard Movie
            2. Blockbuster Movie
            3. 3D Movie
        """);
    }

    public void printCinemaClass() {
        MainView.printMenuContent("""
            Enter the cinema class for this movie

            1. Standard Cinema
            2. Platinum Cinema
            3. IMAX Cinema
                """);
    }

    public void printCineplex() {
        ArrayList <Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());
        String content = "\nEnter the cineplex showing this movie\n";

        for (int i = 0; i < cineplexes.size(); i++){
            String index = String.format("%02d. ", i + 1);
            String payload = String.format(index + cineplexes.get(i).getCineplexName() + "\n");
            payload += (cineplexes.get(i).getCineplexLocation() + "\n");
            content += payload;
        }

        MainView.printMenuContent(content);
    }

    public void printShowingTime(Cinema showingVenue) {
        String content = "\n" + "Showing Time for " + showingVenue.getUUID() + "\n";
        MainView.printMenuContent(content);
    }

    public void appContent(){
        UIHandler.clearScreen();
        this.printMenu();
        this.addNewMovie();
    }

    public void addNewMovie(){
        MovieAgeRating movieAgeRating;
        MovieShowingStatus movieShowingStatus;
        double duration;
        int movieTypeChoice;

        System.out.println("Enter the name of new movie");
        String title = InputHandler.stringHandler();

        int choice = -1;
        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            this.printAgeRating();
            choice = InputHandler.intHandler();
            if (choice < 1 || choice > 7){
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            movieAgeRating = MovieAgeRating.values()[choice - 1];
            this.errorMessage = "";
            break;
        }   while(true);


        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            this.printShowingStatus();
            choice = InputHandler.intHandler();
            if (choice < 1 || choice > 3){
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            movieShowingStatus = MovieShowingStatus.values()[choice - 1];
            this.errorMessage = "";
            break;
        }   while(true);
        

        ArrayList <String> movieCast = new ArrayList<>();
        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            System.out.println("Enter the number of movie cast/casts for " + title);
            int numberOfCasts = InputHandler.intHandler(); 
            if (numberOfCasts < 2) {
                this.errorMessage = "Error! The movie must have minimum 2 casts!";
                continue;
            }
            for (int i = 0; i < numberOfCasts; i++){
                UIHandler.clearScreen();
                System.out.println(this.errorMessage);
                this.printMenu();
                System.out.println("Enter the name of cast " + (i + 1));
                String castName = InputHandler.stringHandler();
                movieCast.add(castName);
            }
            this.errorMessage = "";
            break;
        }   while (true);
        

        UIHandler.clearScreen();
        System.out.println(this.errorMessage);
        this.printMenu();
        System.out.println("Enter the name of the director for " + title);
        String director = InputHandler.stringHandler();
        

        UIHandler.clearScreen();
        System.out.println(this.errorMessage);
        this.printMenu();
        System.out.println("Enter the synopsis for " + title);
        String synopsis = InputHandler.stringHandler();
        

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            System.out.println("Enter duration for " + title);
            duration = InputHandler.doubleHandler();
            if (duration < 0){
                this.errorMessage = "Duration cannot be negative";
                continue;
            }
            this.errorMessage = "";
            break;
        }   while(true);
        
        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            this.printMovieType();
            movieTypeChoice = InputHandler.intHandler();
            if (movieTypeChoice < 0 || movieTypeChoice > 3) {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            this.errorMessage = "";
            break;
        }   while (true);
        
        
        ArrayList <Cinema> showingVenue;
        ArrayList <String> showingVenueUUID = new ArrayList<>();
        ArrayList <ArrayList <Seat>> seatingPlan = new ArrayList<ArrayList<Seat>>();
        ArrayList <DateTime> showingTime = new ArrayList<DateTime>();
        CinemaClass cinemaClass;
        Cineplex cineplex;
        ArrayList <Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());
        Collections.sort(cineplexes);

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            this.printCinemaClass();
            choice = InputHandler.intHandler();
            if (choice < 1 || choice > 3){
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            cinemaClass = CinemaClass.values()[choice-1];
            this.errorMessage = "";
            break;
        }   while(true);
        

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            this.printCineplex();
            choice = InputHandler.intHandler();
            if (choice < 1 || choice > cineplexes.size()){
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            cineplex = cineplexes.get(choice - 1);
            this.errorMessage = "";
            break;
        }   while (true);
        
        
        showingVenue = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            showingVenueUUID.add(showingVenue.get(i).getUUID());
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
        }


        int numOfShowingTimes = showingVenue.size();
        for (int i = 0; i < numOfShowingTimes; i++){
            UIHandler.clearScreen();
            this.printMenu();
            this.printShowingTime(showingVenue.get(i));
            DateTime time = CinemaStaffManager.queryDate();
            showingTime.add(time);
        }        
        
        CinemaStaffManager.movieAdder(title, movieAgeRating, movieShowingStatus, movieCast, director,
                                    synopsis, duration, movieTypeChoice, showingVenueUUID, seatingPlan, showingTime);
    }

    
}
