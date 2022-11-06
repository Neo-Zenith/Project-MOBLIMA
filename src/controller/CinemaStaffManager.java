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
                    MainView.printBoilerPlate("Movie Type for " + movie.getMovieTitle()); 
                    MainView.printMenuContent("""

                        1. Standard Movie
                        2. Blockbuster Movie
                        3. 3D Movie
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
                    else {
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                }   while(true);
                    

            case 3:
                do {
                    UIHandler.clearScreen();
                    MainView.printBoilerPlate("Age Rating for " + movie.getMovieTitle());
                    MainView.printMenuContent("""
                        1. G
                        2. PG
                        3. PG13
                        4. NC16
                        5. M18
                        6. R21
                    """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 6){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
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
                    MainView.printBoilerPlate("Showing Status for " + movie.getMovieTitle());
                    MainView.printMenuContent("""
                            1. Coming Soon
                            2. Preview
                            3. Now Showing
                            4. End of Showing
                            """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 4){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    MovieShowingStatus newShowingStatus = MovieShowingStatus.values()[choice - 1];
                    
                    if (newShowingStatus == MovieShowingStatus.END_OF_SHOWING){
                        System.out.println("Deleting " + movie.getMovieTitle() + " from movie schedule...");
                        Database.MOVIE_SCHEDULE.remove(movieScheduleUUID);
                        DatabaseManager.reloadDatabase();
                        return 1;    
                    }
                    movie.setMovieShowingStatus(newShowingStatus);
                    movieSchedule.setMovieOnShow(movie);
                    DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                    DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                    return 1;
                }   while (true);
            
            case 5:
                do {
                    UIHandler.clearScreen();
                    MainView.printBoilerPlate("Configure Casts for " + movie.getMovieTitle());
                    MainView.printMenuContent("""
                        1. Remove cast
                        2. Add cast
                            """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 2){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }  

                    switch(choice) {
                        case 1: 
                            do {
                                UIHandler.clearScreen();
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
                            System.out.println("Enter the name of the cast to be added.");
                            String castName = InputHandler.stringHandler();
                            movie.getMovieCast().add(castName);
                            movieSchedule.setMovieOnShow(movie);
                            DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                            DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                            return 1;
                    }
                }   while (true);

            case 6:
                UIHandler.clearScreen();
                System.out.println("Enter the new movie director for: " + movie.getMovieTitle());
                String newDirectorName = InputHandler.stringHandler();
                movie.setMovieDirector(newDirectorName);
                movieSchedule.setMovieOnShow(movie);
                DatabaseManager.saveUpdateToDatabase(movieUUID, movie, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                return 1;

            case 7:
                UIHandler.clearScreen();
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
                    MainView.printBoilerPlate("Configure Showing Venues");
                    MainView.printMenuContent("""
                            1. Remove Showing Venue
                            2. Add Showing Venue
                    """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 2){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }

                    switch(choice){
                        case 1:
                            UIHandler.clearScreen();
                            String content = "\nEnter which showing venue to be removed. (Enter ID number)\n";
                            for (int i = 0; i < movieSchedule.getShowingVenues().size(); i ++) {
                                Cinema cinema = movieSchedule.getShowingVenues().get(i);
                                String payload = String.format("ID: %d.\t", i + 1 + "Cinema ID: %s" + cinema.getUUID() + "Class: %s\n" + cinema.getCinemaClass());
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
                                MainView.printMenuContent("""
                                    Enter which showing venue type to be added.
                                    1. Standard Cinema
                                    2. Platinum Cinema
                                    3. IMAX Cinema
                                        """);

                                int newVenueType = InputHandler.intHandler(); 
                                UIHandler.clearScreen();
                                System.out.println("Enter the date time for this cinema type");
                                DateTime showingTime = queryDate();
                                ArrayList <DateTime> newShowingTimes = new ArrayList<DateTime>();
                                newShowingTimes.add(showingTime);
                                
                                if (newVenueType == 1){
                                    CinemaClass cinemaClass = CinemaClass.STANDARD;
                                    ArrayList <Seat> seats = DatabaseManager.initializeSeatData(cinemaClass);
                                    Cinema c = CinemaManager.createStandardCinema(seats);
                                    ArrayList <Cinema> newCinemaList = new ArrayList<Cinema>();
                                    newCinemaList.add(c);
                                    MovieScheduleManager.updateMovieSchedule(movie, newCinemaList ,newShowingTimes);
                                    return 1;
                                } 
                                else if (newVenueType == 2){
                                    CinemaClass cinemaClass = CinemaClass.PLATINUM;
                                    ArrayList <Seat> seats = DatabaseManager.initializeSeatData(cinemaClass);
                                    Cinema c = CinemaManager.createPlatinumCinema(seats);
                                    ArrayList <Cinema> newCinemaList = new ArrayList<Cinema>();
                                    newCinemaList.add(c);
                                    MovieScheduleManager.updateMovieSchedule(movie, newCinemaList ,newShowingTimes);
                                    return 1;
                                } 
                                else if (newVenueType == 3){
                                    CinemaClass cinemaClass = CinemaClass.IMAX;
                                    ArrayList <Seat> seats = DatabaseManager.initializeSeatData(cinemaClass);
                                    Cinema c = CinemaManager.createIMaxCinema(seats);
                                    ArrayList <Cinema> newCinemaList = new ArrayList<Cinema>();
                                    newCinemaList.add(c);
                                    MovieScheduleManager.updateMovieSchedule(movie, newCinemaList ,newShowingTimes);
                                    return 1;
                                } 
                                else {
                                    errorMessage = "Errpr! Please enter a valid input!";
                                    continue;
                                }
                            }   while(true);

                    } 
                } while (true);
            

            case 10:
                do {
                    UIHandler.clearScreen();
                    MainView.printMenuContent("""
                        How would you like to configure showing times?
                        1. Remove showing time
                        2. Add new showing time
                    """);

                    choice = InputHandler.intHandler();
                    if (choice < 1 || choice > 2){
                        errorMessage = "Error! Please enter a valid input!";
                        continue;
                    }
                    switch(choice){
                        case 1:
                            UIHandler.clearScreen();
                            System.out.println("Enter which showing time is to be removed. (Enter ID number)");
                            for (int i = 0; i < movieSchedule.getShowingTime().size(); i++){
                                System.out.println((i+1) + " . " + "Date: " + movieSchedule.getShowingTime().get(i).getYear() + movieSchedule.getShowingTime().get(i).getMonth() + movieSchedule.getShowingTime().get(i).getDate() + " Time: " + movieSchedule.getShowingTime().get(i).getHour() + movieSchedule.getShowingTime().get(i).getMinute());
                            }
                            int showingTimeID = InputHandler.intHandler();
                            if (showingTimeID < 1 || showingTimeID > movieSchedule.getShowingTime().size()){
                                System.out.println("Invalid showing time.");
                                return 1;
                            }
                            movieSchedule.removeShowingTime(showingTimeID - 1);
                            movieSchedule.removeShowingVenue(showingTimeID - 1);
                            System.out.println("Showing time removed.");
                            DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                            return 1;

                        case 2:
                            UIHandler.clearScreen();
                            System.out.println("Enter the time to be added.");

                            System.out.println("year:");
                            int year = InputHandler.intHandler();
                            System.out.println("month:");
                            int month = InputHandler.intHandler();
                            System.out.println("date:");
                            int date = InputHandler.intHandler();
                            System.out.println("hour:");
                            int hour = InputHandler.intHandler();
                            System.out.println("minute:");
                            int minute = InputHandler.intHandler();
                            System.out.println("day:");
                            int day = InputHandler.intHandler();

                            DateTime newShowingTime = new DateTime(minute, hour, day, date, month, year);
                            movieSchedule.addShowingTime(newShowingTime);
                            System.out.println("Showing time added.");
                            DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
                            return 1;
                    }
                } while (true);
            
            default:
                return 0;
        }
    }

    public static int configurePrice(int choice){
        double price;
        switch(choice){
            case 1:
                System.out.println("Enter new price for Standard Cinemas:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultStandardCinemaPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 2:
                System.out.println("Enter new price for Platinum cinemas:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultPlatinumCinemaPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 3:
                System.out.println("Enter new price for IMax cinemas:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultIMaxCinemaPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 4:
                System.out.println("Enter new price for seats:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultSeatPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 5:
                System.out.println("Enter new price for Blockbuster movies:");
                price = InputHandler.doubleHandler();    
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                DatabaseManager.reloadDatabase();
                return 0;
            case 6:
                System.out.println("Enter new price for 3D movies:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefault3DMoviePrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 7:
                System.out.println("Enter new price for standard movies:");
                price = InputHandler.doubleHandler();    
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultStandardMoviePrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 8:
                System.out.println("Enter new price weightage for children:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultChildPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 9:
                System.out.println("Enter new price weightage for students:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultStudentPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 10:
                System.out.println("Enter new price weightage for adults:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultChildPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 11:
                System.out.println("Enter new price weightage for senior citizens:");
                price = InputHandler.doubleHandler();   
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultSeniorCitizenPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 12:
                System.out.println("Enter new price weightage for holidays:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setHolidayPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 13:
                System.out.println("Enter new price weightage for weekends:");
                price = InputHandler.doubleHandler();    
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setWeekendPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            default:
                return 1;
        }
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
        System.out.println("Minute: ");
        int minute = InputHandler.intHandler();
        System.out.println("Hour: ");
        int hour = InputHandler.intHandler();
        System.out.println("Day: ");
        int day = InputHandler.intHandler();
        System.out.println("Date: ");
        int date = InputHandler.intHandler();
        System.out.println("Month: ");
        int month = InputHandler.intHandler();
        System.out.println("Year: ");
        int year = InputHandler.intHandler();
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