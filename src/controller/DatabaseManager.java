package src.controller;

import java.util.*;
import src.database.*;
import src.model.*;
import src.model.enums.*;

/**
 * Controller class for handling all logics related to the database
 * @author Lee Juin
 * @version 1.0
 */
public class DatabaseManager {

    /**
     * Constructor
     */
    public DatabaseManager() {}

    /**
     * Method to load initial cineplex data into database
     * @return {@code true} if initialization is successful, {@code false} otherwise
     */
    public static boolean initializeCineplexData() {
        ArrayList <Cineplex> cineplexs = Database.getValueList(Database.CINEPLEX.values());

        if (cineplexs.size() != 0) {
            return false;
        }

        String cineplexName;
        String cineplexLocation;
        int numOfCinemas;
        ArrayList<Cinema> cinemas = new ArrayList<>();

        // Data 1
        cineplexName = "CATHAY CINEPLEX CAUSEWAY POINT";
        cineplexLocation = """
            1 Woodlands Square
            Level 7, Causeway Point
            Singapore 738099
                """;
        numOfCinemas = 4;
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData(i % 3));
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);

        // Data 2
        cineplexName = "CATHAY CINEPLEX AMK HUB";
        cineplexLocation = """
            53 Ang Mo Kio Ave 3
            Level 4, AMK Hub
            Singapore 569933
                """;
        numOfCinemas = 6;
        cinemas = new ArrayList<>();
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData(i % 3));
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);

        // Data 3
        cineplexName = "CATHAY CINEPLEX JEM";
        cineplexLocation = """
            50 Jurong Gateway Road
            Level 5, Jem
            Singapore 608549
                """;
        numOfCinemas = 5;
        cinemas = new ArrayList<>();
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData(i % 3));
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);

        return true;
    }

    /**
     * Method to load inital cinema data into database
     * @param choice is the choice of {@link CinemaClass} to be initialized
     * @return {@link Cinema} instance created
     */
    public static Cinema initializeCinemaData(int choice) {
        CinemaClass cinemaClass;
        ArrayList <Seat> seats; 

        switch (choice) {
            case 0:
                // Data 1
                cinemaClass = CinemaClass.STANDARD;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createStandardCinema(seats);
            case 1:
                // Data 2
                cinemaClass = CinemaClass.IMAX;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createIMaxCinema(seats);

            case 2:
                // Data 3
                cinemaClass = CinemaClass.PLATINUM;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createPlatinumCinema(seats);
        }
        return null;
    }

    /**
     * Method to initialize the initial seat data into database
     * @param cinemaClass is the {@link CinemaClass} of the cinema the seats belong to
     * @return ArrayList of {@link Seat} instances created
     */
    public static ArrayList <Seat> initializeSeatData(CinemaClass cinemaClass) {
        int numOfSeatsPerRow;
        ArrayList <Seat> seats = new ArrayList<>();

        if (cinemaClass != CinemaClass.PLATINUM) {
            numOfSeatsPerRow = Database.totalNumOfSeats / Database.numOfRows;

            for (int i = 0; i < Database.numOfCoupleRows; i ++) {
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createCoupleSeat());
                }
            }

            for (int i = 0; i < Database.numOfRows - Database.numOfCoupleRows; i ++) {
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createStandardSeat());
                }
            }
        }
        else {
            numOfSeatsPerRow = Database.platinumNumOfSeatsPerRow;
            int numofRows = Database.platinumNumOfRow;

            for (int i = 0; i < numofRows; i ++) {
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createStandardSeat());
                }
            }

        }
        return seats;
    }

    /**
     * Method to initialize the initial movie schedule data into database
     * @return {@code true} if initialization is successful, {@code false} otherwise
     */
    public static boolean initializeMovieScheduleData() {
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        if (movieSchedules.size() != 0) {
            return false;
        }

        Movie movieOnShow;
        ArrayList <Cinema> showingVenue;
        ArrayList <String> showingVenueUUID;
        ArrayList <ArrayList <Seat>> seatingPlan = new ArrayList<ArrayList<Seat>>();
        ArrayList <DateTime> showingTime = new ArrayList<DateTime>();
        CinemaClass cinemaClass;
        Cineplex cineplex;
        ArrayList <Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());
        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());
        Collections.sort(cineplexes);

        // Data 1
        movieOnShow = movies.get(0);
        cinemaClass = CinemaClass.PLATINUM;
        cineplex = cineplexes.get(0);
        showingVenue = new ArrayList<>();
        showingVenueUUID = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            showingVenueUUID.add(showingVenue.get(i).getUUID());
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(00, 14, 2, 21, 11, 2022));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow.getUUID(), showingVenueUUID, seatingPlan, showingTime);

        // Data 2
        showingTime = new ArrayList<>();
        movieOnShow = movies.get(1);
        cinemaClass = CinemaClass.STANDARD;
        cineplex = cineplexes.get(2);
        showingVenue = new ArrayList<>();
        showingVenueUUID = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            showingVenueUUID.add(showingVenue.get(i).getUUID());
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(00, 13, 3, 22, 11, 2022));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow.getUUID(), showingVenueUUID, seatingPlan, showingTime);

        // Data 3  
        showingTime = new ArrayList<>();
        movieOnShow = movies.get(2);
        cinemaClass = CinemaClass.IMAX;
        cineplex = cineplexes.get(1);
        showingVenue = new ArrayList<>();
        showingVenueUUID = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            showingVenueUUID.add(showingVenue.get(i).getUUID());
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(30, 12, 6, 25, 11, 2022));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow.getUUID(), showingVenueUUID, seatingPlan, showingTime);
        return true;
    }

    /**
     * Method to initialize the initial movie data into database
     * @return {@code true} if initialization is successful, {@code false} otherwise
     */
    public static boolean initializeMovie() {
        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());

        if (movies.size() != 0) {
            return false;
        }

        String title;
        MovieAgeRating movieAgeRating;
        MovieShowingStatus movieShowingStatus;
        ArrayList <String> movieCast = new ArrayList<>();
        String director;
        String synopsis;
        double duration;

        // Movie1
        title = "Black Panther: Wakanda Forever (2022)";
        movieAgeRating = MovieAgeRating.PG13;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast.add("Tenoch Huerta Namor");
        movieCast.add("Michael B. Jordan");
        director = "Ryan Coogler";
        synopsis = "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with Nakia and Everett Ross to forge a new path for their beloved kingdom.";
        duration = 161;
        MovieManager.createBlockbusterMovie(title, movieAgeRating, movieShowingStatus, 
                                            movieCast, director, synopsis, duration);

        // Movie2
        title = "Black Panther: Wakanda Forever (2022)";
        movieAgeRating = MovieAgeRating.PG13;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast.add("Tenoch Huerta Namor");
        movieCast.add("Michael B. Jordan");
        director = "Ryan Coogler";
        synopsis = "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with Nakia and Everett Ross to forge a new path for their beloved kingdom.";
        duration = 161;
        MovieManager.createThreeDMovie(title, movieAgeRating, movieShowingStatus, 
                                        movieCast, director, synopsis, duration);

        // Movie3
        title = "The Sacred Riana 2: Bloody Mary (2022)";
        movieAgeRating = MovieAgeRating.M18;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast = new ArrayList<String>();
        movieCast.add("Brooklyn Alif Rea");
        movieCast.add("Frisly Indigo");
        director = "Billy Christian";
        synopsis = "The disappearance of the Riani Doll makes Riana come to Elodia's dormitory.The dormitory is inhabited by a group of female students, who often perform game rituals using mirrors, namely Bloody Mary. However, this game leads to terror that harms the Elodia students. Riani's missing doll is accused of being the mastermind. Riana, who is unraveling the mystery, is trapped in the midst of the conditions to bring Riani home and find answers to the terror alleged to her beloved doll.";
        duration = 113;
        MovieManager.createStandardMovie(title, movieAgeRating, movieShowingStatus, 
                                            movieCast, director, synopsis, duration);
        
        return true;
    }

    /**
     * Method to initialize the initial cinema staff data into database
     * @return {@code true} if initialization is successful, {@code false} otherwise
     */
    public static boolean initalizeCinemaStaff() {
        ArrayList <CinemaStaff> cinemaStaffs = Database.getValueList(Database.CINEMA_STAFF.values());

        if (cinemaStaffs.size() != 0) {
            return false;
        }

        String name = "CinemaStaf";
        String username = "admin";
        String password = "password";
        CinemaStaffManager.createCinemaStaff(name, password, username);

        return true;
    }

    /**
     * Method to initialize the default prices
     */
    public static void initializePrices() {
        Prices prices = new Prices(2,30,3,
        4,3,
        5,2,1.5, 
        1.5,2,1.5,1.5,1.5);
        Database.PRICES = prices;
        DatabaseManager.reloadDatabase();
    }   
    
    /**
     * Generic helper function to save written changes into the database
     * @param <K> is the generic form for key
     * @param <V> is the generic form for value
     * @param UUID is the unique ID of the object to be updated
     * @param object is the object to be updated
     * @param data is the HashMap database to be updated
     */
    public static <K, V> void saveUpdateToDatabase(K UUID, V object, HashMap <K, V> data) {
        data.put(UUID, object);
        DatabaseManager.reloadDatabase();
    }

    /**
     * Helper function to reset the database
     */
    public static void resetDatabase() {
        Database.resetDatabase();
    }

    /**
     * Helper function to reload the database. Useful upon saved changes
     */
    public static void reloadDatabase() {
        Database.writeToDatabase();
        Database.remountDatabase();
    }

    /**
     * Similar to saveUpdateToDatabase, except save to buffer instead.
     * Useful for updating large data
     * @param <K> is the generic form for key
     * @param <V> is the generic form for value
     * @param UUID is the unique ID of the object to be updated
     * @param object is the object to be updated
     * @param data is the HashMap database to be updated
     */
    public static <K, V> void saveUpdateToBuffer(K UUID, V object, HashMap <K, V> data) {
        data.put(UUID, object);
    }
}
