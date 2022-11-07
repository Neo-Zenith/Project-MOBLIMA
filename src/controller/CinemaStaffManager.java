package controller;

import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.enums.CinemaClass;

import model.CinemaStaff;
import model.MovieGoer;

import model.Movie;
import model.BlockbusterMovie;
import model.ThreeDMovie;
import model.StandardMovie;
import model.MovieSchedule;

import model.Cineplex;
import model.Cinema;

import model.Seat;
import model.DateTime;

import database.Database;

import handler.DatabaseHandler;
import handler.InputHandler;
import handler.UIHandler;
import view.*;

import java.security.Permission;
import java.util.*;

public class CinemaStaffManager{
    /*
     * Method to instantiate a cinemaStaff instance and save to database
     * @param name  name of Staff
     * @param password  staff login password
     * @param staffID   staff login id 
     */
    public CinemaStaffManager(){}
    public static CinemaStaff createCinemaStaff(String name, String password, String username){
        String UUID = String.format("SF%03d", DatabaseHandler.generateUUID(Database.CINEMA_STAFF));
        CinemaStaff cinemaStaff = new CinemaStaff(UUID, name, password, username);
        DatabaseManager.saveUpdateToDatabase(UUID, cinemaStaff, Database.CINEMA_STAFF);
        return cinemaStaff;
    }


    public static void movieAdder(String title, MovieAgeRating movieAgeRating, MovieShowingStatus movieShowingStatus, ArrayList<String> movieCast,
    String director, String synopsis, double duration, int movieTypeChoice, ArrayList<Cinema> showingVenue, ArrayList<ArrayList<Seat>> seatingPlan, ArrayList<DateTime> showingTime){
        Movie m;
        if (movieTypeChoice == 1){
            m = MovieManager.createStandardMovie(title, movieAgeRating, movieShowingStatus, 
                                            movieCast, director, synopsis, duration);
        } else if (movieTypeChoice == 2){
            m = MovieManager.createBlockbusterMovie(title, movieAgeRating, movieShowingStatus, 
                                            movieCast, director, synopsis, duration);
        } else if (movieTypeChoice == 3){
            m = MovieManager.createThreeDMovie(title, movieAgeRating, movieShowingStatus, 
                                        movieCast, director, synopsis, duration);
        } else {
            System.out.println("Invalid choice");
            return;
        }
        MovieScheduleManager.createMovieSchedule(m, showingVenue, seatingPlan, showingTime);
        System.out.println("Movie added into database");
    }

    public static int updateExistingMovieDetails(Movie movie, int detail){
        String movieUUID = movie.getUUID();
        MovieSchedule movieSchedule = MovieScheduleManager.filterMovieSchedulesByMovie(movie);
        String movieScheduleUUID = movieSchedule.getUUID();

        String errorMessage = "";

        int choice = -1;
        switch(detail){
            case 1:
                UIHandler.clearScreen();
                System.out.println(errorMessage);
                MainView.printBoilerPlate("Configure Movie Title");
                System.out.println("Enter the new name of the movie: ");
                String newMovieName = InputHandler.stringHandler();
                movie.setMovieTitle(newMovieName);
                movieSchedule.setMovieOnShow(movie);

                DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                return 1;

            case 2:
                do {
                    UIHandler.clearScreen();
                    System.out.println(errorMessage);
                    MainView.printBoilerPlate("Configure Movie Type for " + movie.getMovieTitle()); 
                    MainView.printMenuContent("""

                        1. Standard Movie
                        2. Blockbuster Movie
                        3. 3D Movie
                        4. Return
                    """);
        
                    int newMovieType = InputHandler.intHandler();  

                    String oldMovieTitle = movie.getMovieTitle();
                    MovieAgeRating oldMovieAgeRating = movie.getMovieAgeRating();
                    MovieShowingStatus oldShowingStatus = movie.getMovieShowingStatus();
                    ArrayList<String> oldMovieCast = movie.getMovieCast(); 
                    String oldMovieDirector = movie.getMovieDirector();
                    String oldMovieSynopsis = movie.getMovieSynopsis();
                    double oldMovieDuration = movie.getMovieDuration();

                    if (newMovieType == 1) {
                        Movie newMovie = new StandardMovie(movieUUID, oldMovieTitle, oldMovieAgeRating,
                                                            oldShowingStatus, oldMovieCast, oldMovieDirector, oldMovieSynopsis,
                                                            oldMovieDuration);
                        movieSchedule.setMovieOnShow(newMovie);
                        DatabaseManager.saveUpdateToDatabase(movieUUID, newMovie, Database.MOVIE);
                        DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                        return 1;
                    } 
                    else if (newMovieType == 2) {
                        Movie newMovie = new BlockbusterMovie(movieUUID, oldMovieTitle, oldMovieAgeRating,
                                                            oldShowingStatus, oldMovieCast, oldMovieDirector, oldMovieSynopsis,
                                                            oldMovieDuration);
                        movieSchedule.setMovieOnShow(newMovie);
                        DatabaseManager.saveUpdateToDatabase(movieUUID, newMovie, Database.MOVIE);
                        DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                        return 1;
                    } 
                    else if (newMovieType == 3){
                        Movie newMovie = new ThreeDMovie(movieUUID, oldMovieTitle, oldMovieAgeRating,
                                                            oldShowingStatus, oldMovieCast, oldMovieDirector, oldMovieSynopsis,
                                                            oldMovieDuration);
                        movieSchedule.setMovieOnShow(newMovie);
                        DatabaseManager.saveUpdateToDatabase(movieUUID, newMovie, Database.MOVIE);
                        DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                        return 1;
                    } 
                    else if (newMovieType == 4) {
                        errorMessage = "";
                        return 1;
                    }
                    else {
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                }   while(true);
                    

            case 3:
                do {
                    UIHandler.clearScreen();
                    System.out.println(errorMessage);
                    MainView.printBoilerPlate("Configure Age Rating for " + movie.getMovieTitle());
                    MainView.printMenuContent("""
                        1. G
                        2. PG
                        3. PG13
                        4. NC16
                        5. M18
                        6. R21
                        7. Return
                    """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 7){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }

                    if (choice == 7) {
                        errorMessage = "";
                        return 1;
                    }

                    MovieAgeRating newMovieAgeRating = MovieAgeRating.values()[choice - 1];
                    movie.setMovieAgeRating(newMovieAgeRating);
                    movieSchedule.setMovieOnShow(movie);
                    DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                    DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                    return 1;
                }   while(true);
                
            case 4:
                do {
                    UIHandler.clearScreen();
                    System.out.println(errorMessage);
                    MainView.printBoilerPlate("Configure Showing Status for " + movie.getMovieTitle());
                    MainView.printMenuContent("""
                            1. Coming Soon
                            2. Preview
                            3. Now Showing
                            4. End of Showing
                            5. Return
                            """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 5){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }

                    if (choice == 5) {
                        errorMessage = "";
                        return 1;
                    }

                    MovieShowingStatus newShowingStatus = MovieShowingStatus.values()[choice - 1];
                    movie.setMovieShowingStatus(newShowingStatus);
                    movieSchedule.setMovieOnShow(movie);
                    
                    if (newShowingStatus == MovieShowingStatus.END_OF_SHOWING){
                        System.out.println("Deleting " + movie.getMovieTitle() + " from movie schedule...");
                        ArrayList <Cinema> resetShowingVenues = new ArrayList<>();
                        ArrayList <ArrayList<Seat>> resetSeat = new ArrayList<>();
                        ArrayList <DateTime> resetShowingTime = new ArrayList<>();
                        movieSchedule.setSeatingPlan(resetSeat);
                        movieSchedule.setShowingTime(resetShowingTime);
                        movieSchedule.setShowingVenues(resetShowingVenues);   
                    }
                    DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                    DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                    return 1;
                }   while (true);
            
            case 5:
                do {
                    UIHandler.clearScreen();
                    System.out.println(errorMessage);
                    MainView.printBoilerPlate("Configure Casts for " + movie.getMovieTitle());
                    MainView.printMenuContent("""
                        1. Remove cast
                        2. Add cast
                        3. Return
                            """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 3){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }  

                    switch(choice) {
                        case 1: 
                            do {
                                UIHandler.clearScreen();
                                System.out.println(errorMessage);
                                MainView.printBoilerPlate("Remove Movie Casts");
                                String content = "\nEnter which cast number is to be removed. (Enter Cast Number)\n";

                                for (int i = 0; i < movie.getMovieCast().size(); i++){
                                    String payload = String.format("Cast number: %d", (i + 1) + "\t" + movie.getMovieCast().get(i) + "\n");
                                    content = content + payload;
                                }
                                MainView.printMenuContent(content);
                                int castNumber = InputHandler.intHandler();
                                if (castNumber < 1 || castNumber > movie.getMovieCast().size()){
                                    errorMessage = "Error! Please enter a valid input!";
                                    continue;
                                }
                                movie.getMovieCast().remove(castNumber - 1);
                                movieSchedule.setMovieOnShow(movie);
                                DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                                DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                                return 1;
                            }   while(true);
                            
                        case 2:
                            UIHandler.clearScreen();
                            System.out.println(errorMessage);
                            MainView.printBoilerPlate("Add Movie Cast");
                            System.out.println("Enter the name of the cast to be added.");
                            String castName = InputHandler.stringHandler();
                            movie.getMovieCast().add(castName);
                            movieSchedule.setMovieOnShow(movie);
                            DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                            DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                            return 1;
                        
                        default:
                            errorMessage = "";
                            return 1;
                    }
                }   while (true);

            case 6:
                UIHandler.clearScreen();
                System.out.println(errorMessage);
                MainView.printBoilerPlate("Configure Movie Director");
                System.out.println("Enter the new movie director for: " + movie.getMovieTitle());
                String newDirectorName = InputHandler.stringHandler();
                movie.setMovieDirector(newDirectorName);
                movieSchedule.setMovieOnShow(movie);
                DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                return 1;

            case 7:
                UIHandler.clearScreen();
                System.out.println(errorMessage);
                MainView.printBoilerPlate("Configure Movie Synopsis");
                System.out.println("Enter the new synopsis of " + movie.getMovieTitle() + ".");
                String newSynopsis = InputHandler.stringHandler();
                movie.setMovieSynopsis(newSynopsis);
                movieSchedule.setMovieOnShow(movie);
                DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                return 1;

            case 8:
                do {
                    UIHandler.clearScreen();
                    System.out.println(errorMessage);
                    MainView.printBoilerPlate("Configure Movie Duration");
                    System.out.println("Enter new showing duration for " + movie.getMovieTitle() + ".");
                    double newMovieDuration = InputHandler.doubleHandler();
                    if (newMovieDuration < 0) {
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    movie.setMovieDuration(newMovieDuration);
                    movieSchedule.setMovieOnShow(movie);
                    DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                    DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                    return 1;  
                } while(true);
                

            case 9:
                do {
                    UIHandler.clearScreen();
                    System.out.println(errorMessage);
                    MainView.printBoilerPlate("Configure Schedule");
                    MainView.printMenuContent("""
                            1. Remove Schedule
                            2. Add Schedule
                            3. Return
                    """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 3){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    errorMessage = "";
                    switch(choice){
                        case 1:
                            UIHandler.clearScreen();
                            System.out.println(errorMessage);
                            MainView.printBoilerPlate("Remove Schedule");
                            String content = "\nEnter which schedule to be removed. (Enter ID number)\n";
                            for (int i = 0; i < movieSchedule.getShowingVenues().size(); i ++) {
                                Cinema cinema = movieSchedule.getShowingVenues().get(i);
                                DateTime showingTime = movieSchedule.getShowingTime().get(i);
                                String payload = String.format("ID: %d.\n", (i + 1));
                                payload += String.format("Cinema ID: %s", cinema.getUUID());
                                payload += String.format("\tClass: %s\n", cinema.getCinemaClass());
                                payload += (showingTime.getTimeNow() + "\n\n");

                                content = content + payload;
                            }
                            MainView.printMenuContent(content);
                            int venueID = InputHandler.intHandler();
                            if (venueID < 1 || venueID > movieSchedule.getShowingVenues().size()){
                                errorMessage = "Error! Please enter a valid input!";
                                continue;
                            }
                            movieSchedule.removeShowingVenue(venueID - 1);
                            movieSchedule.removeShowingTime(venueID - 1);
                            DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                            return 1;

                        case 2:
                            do {
                                UIHandler.clearScreen();
                                System.out.println(errorMessage);
                                MainView.printBoilerPlate("Add Schedule");
                                ArrayList <Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());
                                content = "\nEnter the cineplex showing this movie\n";

                                for (int i = 0; i < cineplexes.size(); i++){
                                    String index = String.format("%02d. ", i + 1);
                                    String payload = String.format(index + cineplexes.get(i).getCineplexName() + "\n");
                                    payload += (cineplexes.get(i).getCineplexLocation() + "\n");
                                    content += payload;
                                }
                                content += String.format("%02d. ", cineplexes.size() + 1);
                                content += "Return";

                                MainView.printMenuContent(content);
                                int cineplexChoice = InputHandler.intHandler();
                                if (cineplexChoice < 0 || cineplexChoice > cineplexes.size()) {
                                    errorMessage = "Error! Please enter a valid input!";
                                    continue;
                                }

                                if (cineplexChoice == cineplexes.size() + 1) {
                                    errorMessage = "";
                                    return 1;
                                }

                                Cineplex cineplex = cineplexes.get(cineplexChoice - 1);

                                UIHandler.clearScreen();
                                System.out.println(errorMessage);
                                MainView.printBoilerPlate("Configure New Showing Venue Class");
                                MainView.printMenuContent("""
                                    Enter which showing venue type to be added.
                                    1. Standard Cinema
                                    2. Platinum Cinema
                                    3. IMAX Cinema
                                    4. Return
                                        """);
                                int newVenueType = InputHandler.intHandler();
                                if (newVenueType < 0 || newVenueType > CinemaClass.values().length) {
                                    errorMessage = "Error! Please enter a valid input!";
                                    continue;
                                }

                                if (newVenueType == CinemaClass.values().length + 1) {
                                    errorMessage = "";
                                    return 1;
                                }

                                CinemaClass cinemaClass;
                                ArrayList <DateTime> newShowingTimes = new ArrayList<DateTime>();
                                
                                if (newVenueType == 1) {
                                    cinemaClass = CinemaClass.STANDARD;
                                } 
                                else if (newVenueType == 2){
                                    cinemaClass = CinemaClass.PLATINUM;
                                } 
                                else {
                                    cinemaClass = CinemaClass.IMAX;
                                } 
                                ArrayList <Cinema> cinemas = CinemaManager.filterCinemaByClass(cinemaClass, cineplex);
    
                                for (int i = 0; i < cinemas.size(); i ++) {
                                    UIHandler.clearScreen();
                                    System.out.println(errorMessage);
                                    MainView.printBoilerPlate("Configure New Showing Time");
                                    System.out.println("Enter the showing time for " + cinemas.get(i).getUUID());
                                    DateTime showingTime = queryDate();
                                    newShowingTimes.add(showingTime);
                                }
                                MovieScheduleManager.updateMovieSchedule(movie, cinemas, newShowingTimes);
                                return 1;
                            }   while(true);
                        
                        default:
                            errorMessage = "";
                            return 1;
                    } 
                } while (true);
    
            default:
                return 0;
        }
    }

    public static int configurePrice(int choice){
        double price;
        String errorMessage = "";

        do {
            UIHandler.clearScreen();
            System.out.println(errorMessage);
            switch(choice){
                case 1:
                    MainView.printBoilerPlate("Configure Standard Cinema Price");
                    System.out.println("Enter new price for Standard Cinemas:");
                    price = InputHandler.doubleHandler();
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultStandardCinemaPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 2:
                    MainView.printBoilerPlate("Configure Platinum Cinema Price");
                    System.out.println("Enter new price for Platinum cinemas:");
                    price = InputHandler.doubleHandler();
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultPlatinumCinemaPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 3:
                    MainView.printBoilerPlate("Configure IMAX Cinema Price");
                    System.out.println("Enter new price for IMAX cinemas:");
                    price = InputHandler.doubleHandler();
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultIMaxCinemaPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 4:
                    MainView.printBoilerPlate("Configure Seat Price");
                    System.out.println("Enter new price for cinema seats:");
                    price = InputHandler.doubleHandler();
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultSeatPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 5:
                    MainView.printBoilerPlate("Configure Blockbuster Movie Price");
                    System.out.println("Enter new price for Blockbuster Movies:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultBlockbusterMoviePrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 6:
                    MainView.printBoilerPlate("Configure 3D Movie Price");
                    System.out.println("Enter new price for 3D Movies:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefault3DMoviePrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 7:
                    MainView.printBoilerPlate("Configure Standard Movie Price");
                    System.out.println("Enter new price for Standard Movies:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultStandardMoviePrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 8:
                    MainView.printBoilerPlate("Configure Child Ticket Price");
                    System.out.println("Enter new price for Child Ticket:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultChildPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 9:
                    MainView.printBoilerPlate("Configure Student Ticket Price");
                    System.out.println("Enter new price for Student Ticket:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultStudentPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 10:
                    MainView.printBoilerPlate("Configure Adult Ticket Price");
                    System.out.println("Enter new price for Adult Ticket:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultAdultPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 11:
                    MainView.printBoilerPlate("Configure Senior Citizen Ticket Price");
                    System.out.println("Enter new price for Senior Citizen Ticket:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setDefaultSeniorCitizenPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 12:
                    MainView.printBoilerPlate("Configure Holiday Ticket Price");
                    System.out.println("Enter new price for Holiday Ticket:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setHolidayPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                case 13:
                    MainView.printBoilerPlate("Configure Weekend Ticket Price");
                    System.out.println("Enter new price for Weekend Ticket:");
                    price = InputHandler.doubleHandler();    
                    if (price < 0){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    Database.PRICES.setWeekendPrice(price);
                    DatabaseManager.reloadDatabase();
                    return 1;
                default:
                    return 1;
            }
        }   while(true);
    }

    public static int configureHoliday(int choice){
        DateTime holiday;
        printHolidayList();
        switch(choice){
            case 1:
                System.out.println("Enter holiday date time to be added");
                holiday = queryDate();
                
                for (int i = 0; i < Database.holidays.size(); i++){
                    if (Database.holidays.get(i).getYear() == holiday.getYear() && Database.holidays.get(i).getMonth() == holiday.getMonth() && Database.holidays.get(i).getDate() == holiday.getDate() && Database.holidays.get(i).getHour() == holiday.getHour() && Database.holidays.get(i).getMinute() == holiday.getMinute() && Database.holidays.get(i).getDay() == holiday.getDay()){
                    System.out.println("Holiday already exists!");
                    printHolidayList();
                    return 1;
                }
                }
                Database.holidays.add(holiday);
                System.out.println("Holiday Added");
                printHolidayList();
                DatabaseManager.reloadDatabase();
                return 0;

            case 2:       
                System.out.println("Enter holiday to be removed");
                holiday = queryDate();
                if (Database.holidays.size() == 0){
                    System.out.println("Holiday list is empty!");
                    return 1;
                }
                for (int i = 0; i < Database.holidays.size(); i++){
                    if (Database.holidays.get(i).getYear() == holiday.getYear() && Database.holidays.get(i).getMonth() == holiday.getMonth() && Database.holidays.get(i).getDate() == holiday.getDate() && Database.holidays.get(i).getHour() == holiday.getHour() && Database.holidays.get(i).getMinute() == holiday.getMinute() && Database.holidays.get(i).getDay() == holiday.getDay()){
                        Database.holidays.remove(i);
                        System.out.println("Holiday removed");
                        printHolidayList();
                        DatabaseManager.reloadDatabase();
                        return 0;
                    }
                }
                System.out.println("Holiday cannot be found");
                printHolidayList();
                return 1;
            
            default:
                System.out.println("Invalid choice.");
                return 1;
            }
        }
        
     

    
    public static void printHolidayList(){
        if (Database.holidays.size() == 0){
            System.out.println("Holiday list is empty!");
            return;
        }
        System.out.println("Here are the list of holidays:");
        for (int i = 0; i < Database.holidays.size(); i++){
            Database.holidays.get(i).printTime();
        }
        System.out.println("");
    }

    public static DateTime queryDate(){
        System.out.print("Year: ");
        int year = InputHandler.intHandler();
        System.out.print("Month: ");
        int month = InputHandler.intHandler();
        System.out.print("Date: ");
        int date = InputHandler.intHandler();
        System.out.print("Day: ");
        int day = InputHandler.intHandler();
        System.out.print("Hour: ");
        int hour = InputHandler.intHandler();
        System.out.print("Minute: ");
        int minute = InputHandler.intHandler();
        
        DateTime holiday = new DateTime(minute, hour, day, date, month, year);
        return holiday;
    }

    public static void optInOut(int choice, boolean optInOut){
        if (choice == 1) {
            Database.PERMISSION.setOverallRatingsPermission(optInOut);
        }
        else {
            Database.PERMISSION.setMovieSalesPermission(optInOut);
        }
        DatabaseManager.reloadDatabase();
    }
}