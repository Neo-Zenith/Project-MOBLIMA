package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;

import database.Database;
import model.Seat;
import model.enums.CinemaClass;
import model.Cinema;
import model.Cineplex;
import model.enums.SeatType;
import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.Movie;
import model.DateTime;

public class DatabaseManager {

    public DatabaseManager() {}
    
    /**
     * Initializes dummy data for cinplexes.
     */
    public static void initializeCineplexData() {
        String cineplexName;
        String cineplexLocation;
        int numOfCinemas;
        ArrayList <Cinema> cinemas = new ArrayList<>();

        // Data 1
        cineplexName = "CATHAY CINEPLEX CAUSEWAY POINT";
        cineplexLocation = """
            1 Woodlands Square
            Level 7, Causeway Point
            Singapore 738099
                """;
        numOfCinemas = 4;
        for (int i = 0; i < numOfCinemas; i ++) {
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
        for (int i = 0; i < numOfCinemas; i ++) {
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
        for (int i = 0; i < numOfCinemas; i ++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);
    }

    /**
     * Initializes dummy data for cinemas.
     * @return {@link Cinema} to be passed to Cineplex for initialization
     */
    public static Cinema initializeCinemaData() {
        CinemaClass cinemaClass;
        double cinemaPrice;
        ArrayList <Seat> seats;

        Random rand = new Random();
        int choice = rand.nextInt(3);

        switch (choice) {
            case 0:
                // Data 1
                cinemaClass = CinemaClass.STANDARD;
                cinemaPrice = Database.defaultStandardCinemaPrice;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createStandardCinema(cinemaClass, cinemaPrice, seats);
            case 1:
                // Data 2
                cinemaClass = CinemaClass.IMAX;
                cinemaPrice = Database.defaultIMaxCinemaPrice;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createIMaxCinema(cinemaClass, cinemaPrice, seats);
                
            case 2:
                // Data 3
                cinemaClass = CinemaClass.PLATINUM;
                cinemaPrice = Database.defautltPlatinumCinemaPrice;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createPlatinumCinema(cinemaClass, cinemaPrice, seats);
        }
        return null;
    }

    /**
     * Initializes dummy data for seats
     * @return ArrayList of {@link Seat} to be passed to {@link Cinema} for initialization
     */
    public static ArrayList <Seat> initializeSeatData(CinemaClass cinemaClass) {
        SeatType seatType;
        double seatPrice = Database.defaultSeatPrice;;
        int numOfSeatsPerRow;
        ArrayList <Seat> seats = new ArrayList<>();

        if (cinemaClass != CinemaClass.PLATINUM) {
            numOfSeatsPerRow = Database.totalNumOfSeats / Database.numOfRows;

            for (int i = 0; i < Database.numOfCoupleRows; i ++) {
                seatType = SeatType.COUPLE;
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createCoupleSeat(seatType, seatPrice));
                }
            }

            for (int i = 0; i < Database.numOfRows - Database.numOfCoupleRows; i ++) {
                seatType = SeatType.STANDARD;
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createStandardSeat(seatType, seatPrice));
                }
            }
        }
        else {
            numOfSeatsPerRow = Database.platinumNumOfSeatsPerRow;
            int numofRows = Database.platinumNumOfRow;

            for (int i = 0; i < numofRows; i ++) {
                seatType = SeatType.STANDARD;
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createStandardSeat(seatType, seatPrice));
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
        for (int i = 0; i < showingVenue.size(); i ++) {
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
        for (int i = 0; i < showingVenue.size(); i ++) {
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
        for (int i = 0; i < showingVenue.size(); i ++) {
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(30, 12, 4, 25, 1, 2023));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow, showingVenue, seatingPlan, showingTime);
    }
    
    public static void initializeMovie() {

        // Movie1
        String title1 = "The Conjuring";
        String movieType1 = "Blockbuster";
        MovieAgeRating ageRating1 = MovieAgeRating.M18;
        MovieShowingStatus status1 = MovieShowingStatus.NOW_SHOWING;
        ArrayList<String> cast = new ArrayList<String>();
        cast.add("Yeek");
        cast.add("Jerick");
        String director = "LeeJuin";
        String synopsis = "Good";
        Double duration = 123.0;
        MovieManager manager = new MovieManager();
        manager.createMovie(title1, movieType1, ageRating1, status1, cast, director, synopsis, duration);

        // Movie2
        String title2 = "Zootopia";
        String movieType2 = "ThreeD";
        MovieAgeRating ageRating2 = MovieAgeRating.G;
        MovieShowingStatus status2 = MovieShowingStatus.NOW_SHOWING;
        ArrayList<String> cast2 = new ArrayList<String>();
        cast.add("Yeek");
        cast.add("Jerick");
        String director2 = "LeeJuin";
        String synopsis2 = "Good";
        Double duration2 = 155.0;
        manager.createMovie(title2, movieType2, ageRating2, status2, cast2, director2, synopsis2, duration2);

        // Movie3
        String title3 = "Spiderman";
        String movieType3 = "Standard";
        MovieAgeRating ageRating3 = MovieAgeRating.G;
        MovieShowingStatus status3 = MovieShowingStatus.NOW_SHOWING;
        ArrayList<String> cast3 = new ArrayList<String>();
        cast.add("Yeek");
        cast.add("Jerick");
        String director3 = "LeeJuin";
        String synopsis3 = "Good";
        Double duration3 = 155.0;
        manager.createMovie(title3, movieType3, ageRating3, status3, cast3, director3, synopsis3, duration3);

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
