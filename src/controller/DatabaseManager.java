package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Collections;

import database.Database;
import model.Seat;
import model.enums.CinemaClass;
import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.Cinema;
import model.Cineplex;
import model.enums.SeatType;
import model.Movie;
import model.DateTime;

public class DatabaseManager {

    public DatabaseManager() {
    }

    /**
     * Initializes dummy data for cinplexes.
     */
    public static void initializeCineplexData() {
        String cineplexName;
        int numOfCinemas;
        ArrayList<Cinema> cinemas = new ArrayList<>();

        // Data 1
        cineplexName = "Cathay Cineplex";
        numOfCinemas = 5;
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas);

        // Data 2
        cineplexName = "Shaw Theatre";
        numOfCinemas = 3;
        cinemas = new ArrayList<>();
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas);

        // Data 3
        cineplexName = "Golden Village";
        numOfCinemas = 7;
        cinemas = new ArrayList<>();
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas);
    }

    /**
     * Initializes dummy data for cinemas.
     * 
     * @return {@link Cinema} to be passed to Cineplex for initialization
     */
    public static Cinema initializeCinemaData() {
        CinemaClass cinemaClass;
        double cinemaPrice;
        ArrayList<Seat> seats = initializeSeatData();

        Random rand = new Random();
        int choice = rand.nextInt(3);

        switch (choice) {
            case 0:
                // Data 1
                cinemaClass = CinemaClass.STANDARD;
                cinemaPrice = 8;
                return CinemaManager.createStandardCinema(cinemaClass, cinemaPrice, seats);
            case 1:
                // Data 2
                cinemaClass = CinemaClass.IMAX;
                cinemaPrice = 8;
                return CinemaManager.createIMaxCinema(cinemaClass, cinemaPrice, seats);

            case 2:
                // Data 3
                cinemaClass = CinemaClass.PLATINUM;
                cinemaPrice = 8;
                return CinemaManager.createPlatinumCinema(cinemaClass, cinemaPrice, seats);
        }
        return null;
    }

    /**
     * Initializes dummy data for seats
     * 
     * @return ArrayList of {@link Seat} to be passed to {@link Cinema} for
     *         initialization
     */
    public static ArrayList<Seat> initializeSeatData() {
        SeatType seatType;
        double seatPrice;
        int numOfSeatsPerRow = Database.totalNumOfSeats / Database.numOfRows;

        ArrayList<Seat> seats = new ArrayList<>();

        for (int i = 0; i < Database.numOfCoupleRows; i++) {
            seatType = SeatType.COUPLE;
            seatPrice = 5;
            for (int j = 0; j < numOfSeatsPerRow; j++) {
                seats.add(SeatManager.createCoupleSeat(seatType, seatPrice));
            }
        }

        for (int i = 0; i < Database.numOfRows - Database.numOfCoupleRows; i++) {
            seatType = SeatType.STANDARD;
            seatPrice = 4;
            for (int j = 0; j < numOfSeatsPerRow; j++) {
                seats.add(SeatManager.createStandardSeat(seatType, seatPrice));
            }
        }

        return seats;
    }

    public static void initializeMovieScheduleData() {
        Movie movieOnShow;
        ArrayList<Cinema> showingVenue;
        ArrayList<ArrayList<Seat>> seatingPlan = new ArrayList<ArrayList<Seat>>();
        DateTime showingTime;
        CinemaClass cinemaClass;
        Cineplex cineplex;
        ArrayList<Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());
        Collections.sort(cineplexes);

        // Data 1
        movieOnShow = Movie.movies.get(0);
        cinemaClass = CinemaClass.PLATINUM;
        cineplex = cineplexes.get(0);
        showingTime = new DateTime(00, 14, 6, 21, 11, 2022);
        showingVenue = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow, showingVenue, seatingPlan, showingTime);

        // Data 2
        movieOnShow = Movie.movies.get(1);
        cinemaClass = CinemaClass.STANDARD;
        cineplex = cineplexes.get(2);
        showingTime = new DateTime(00, 13, 4, 22, 12, 2022);
        showingVenue = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow, showingVenue, seatingPlan, showingTime);

        // Data 3
        movieOnShow = Movie.movies.get(2);
        cinemaClass = CinemaClass.IMAX;
        cineplex = cineplexes.get(1);
        showingTime = new DateTime(30, 12, 4, 25, 1, 2023);
        showingVenue = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
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

    public static <K, V> void saveUpdateToDatabase(K UUID, V object, HashMap<K, V> data) {
        data.put(UUID, object);
    }

    public static void resetDatabase() {
        Database.resetDatabase();
    }
}
