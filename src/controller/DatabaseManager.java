package controller;

import java.util.*;
import database.*;
import model.*;
import model.enums.*;


public class DatabaseManager {

    public DatabaseManager() {
    }

    /**
     * Initializes dummy data for cinplexes.
     */
    public static void initializeCineplexData() {
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
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);

        // Data 2
        cineplexName = "CATHAY CINEPLEX AMK HUB";
        cineplexLocation = """
            53 Ang Mo Kio Ave 3
            Level 4, AMK Hub
            Singapore 569933
                """;
        numOfCinemas = 3;
        cinemas = new ArrayList<>();
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);

        // Data 3
        cineplexName = "CATHAY CINEPLEX JEM";
        cineplexLocation = """
            50 Jurong Gateway Road
            Level 5, Jem
            Singapore 608549
                """;
        numOfCinemas = 4;
        cinemas = new ArrayList<>();
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);
    }

    /**
     * Initializes dummy data for cinemas.
     * 
     * @return {@link Cinema} to be passed to Cineplex for initialization
     */
    public static Cinema initializeCinemaData() {
        CinemaClass cinemaClass;
        ArrayList <Seat> seats;

        Random rand = new Random();
        int choice = rand.nextInt(3);

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
     * Initializes dummy data for seats
     * 
     * @return ArrayList of {@link Seat} to be passed to {@link Cinema} for
     *         initialization
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

    public static void initializeMovieScheduleData() {
        Movie movieOnShow;
        ArrayList <Cinema> showingVenue;
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
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(00, 14, 6, 21, 11, 2022));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow, showingVenue, seatingPlan, showingTime);

        // Data 2
        showingTime = new ArrayList<>();
        movieOnShow = movies.get(1);
        cinemaClass = CinemaClass.STANDARD;
        cineplex = cineplexes.get(2);
        showingVenue = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(00, 13, 4, 22, 12, 2022));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow, showingVenue, seatingPlan, showingTime);

        // Data 3  
        showingTime = new ArrayList<>();
        movieOnShow = movies.get(2);
        cinemaClass = CinemaClass.IMAX;
        cineplex = cineplexes.get(1);
        showingVenue = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(30, 12, 4, 25, 1, 2023));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow, showingVenue, seatingPlan, showingTime);
    }

    public static void initializeMovie() {
        String title;
        MovieAgeRating movieAgeRating;
        MovieShowingStatus movieShowingStatus;
        ArrayList <String> movieCast = new ArrayList<>();
        String director;
        String synopsis;
        double duration;

        // Movie1
        title = "The Conjuring";
        movieAgeRating = MovieAgeRating.M18;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast.add("Yeek");
        movieCast.add("Jerick");
        director = "LeeJuin";
        synopsis = "Good";
        duration = 123.0;
        MovieManager.createBlockbusterMovie(title, movieAgeRating, movieShowingStatus, 
                                            movieCast, director, synopsis, duration);

        // Movie2
        title = "Zootopia";
        movieAgeRating = MovieAgeRating.G;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast = new ArrayList<String>();
        movieCast.add("Yeek");
        movieCast.add("Jerick");
        director = "LeeJuin";
        synopsis = "Good";
        duration = 155.0;
        MovieManager.createThreeDMovie(title, movieAgeRating, movieShowingStatus, 
                                        movieCast, director, synopsis, duration);

        // Movie3
        title = "Spiderman";
        movieAgeRating = MovieAgeRating.G;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast = new ArrayList<String>();
        movieCast.add("Yeek");
        movieCast.add("Jerick");
        director = "LeeJuin";
        synopsis = "Good";
        duration = 155.0;
        MovieManager.createStandardMovie(title, movieAgeRating, movieShowingStatus, 
                                            movieCast, director, synopsis, duration);

    }

    public static Prices initializePrices() {
        Prices prices = new Prices(2,30,3,
        4,3,
        5,2,1.5, 
        1.5,2,1.5,1.5,1.5);
        return prices;
    }   
    
    public static <K, V> void saveUpdateToDatabase(K UUID, V object, HashMap <K, V> data) {
        data.put(UUID, object);
        Database.writeToDatabase();
        Database.remountDatabase();
    }

    public static void resetDatabase() {
        Database.resetDatabase();
    }
}
