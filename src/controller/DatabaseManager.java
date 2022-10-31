package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import database.Database;
import model.Seat;
import model.enums.CinemaClass;
import model.Cinema;
import model.enums.SeatType;

public class DatabaseManager {

    public DatabaseManager() {}
    
    /**
     * Initializes dummy data for cinplexes.
     */
    public static void initializeCineplexData() {
        String cineplexName;
        int numOfCinemas;
        ArrayList <Cinema> cinemas = new ArrayList<>();

        // Data 1
        cineplexName = "Cathay Cineplex";
        numOfCinemas = 5;
        for (int i = 0; i < numOfCinemas; i ++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas);

        // Data 2
        cineplexName = "Shaw Theatre";
        numOfCinemas = 3;
        for (int i = 0; i < numOfCinemas; i ++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas);

        // Data 3
        cineplexName = "Golden Village";
        numOfCinemas = 7;
        for (int i = 0; i < numOfCinemas; i ++) {
            cinemas.add(initializeCinemaData());
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas);
    }

    /**
     * Initializes dummy data for cinemas.
     * @return {@link Cinema} to be passed to Cineplex for initialization
     */
    public static Cinema initializeCinemaData() {
        CinemaClass cinemaClass;
        double cinemaPrice;
        ArrayList <Seat> seats = initializeSeatData();

        Random rand = new Random();
        int choice = rand.nextInt(3);

        switch (choice) {
            case 0:
                // Data 1
                cinemaClass = CinemaClass.PLATINUM;
                cinemaPrice = 8;
                return CinemaManager.createPlatinumCinema(cinemaClass, cinemaPrice, seats);
            case 1:
                // Data 2
                cinemaClass = CinemaClass.PLATINUM;
                cinemaPrice = 8;
                return CinemaManager.createPlatinumCinema(cinemaClass, cinemaPrice, seats);
                
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
     * @return ArrayList of {@link Seat} to be passed to {@link Cinema} for initialization
     */
    public static ArrayList <Seat> initializeSeatData() {
        SeatType seatType;
        double seatPrice;
        int numOfSeatsPerRow = Database.totalNumOfSeats / Database.numOfRows;

        ArrayList <Seat> seats = new ArrayList<>();

        for (int i = 0; i < Database.numOfCoupleRows; i ++) {
            seatType = SeatType.COUPLE;
            seatPrice = 5;
            for (int j = 0; j < numOfSeatsPerRow; j ++) {
                seats.add(SeatManager.createCoupleSeat(seatType, seatPrice));
            }
        }

        for (int i = 0; i < Database.numOfRows - Database.numOfCoupleRows; i ++) {
            seatType = SeatType.STANDARD;
            seatPrice = 4;
            for (int j = 0; j < numOfSeatsPerRow; j ++) {
                seats.add(SeatManager.createStandardSeat(seatType, seatPrice));
            }
        }

        return seats;
    }


    public static void initializeMovieScheduleData() {

    }

    
    public static <K, V> void saveUpdateToDatabase(K UUID, V object, HashMap <K, V> data) {
        data.put(UUID, object);
    }

    public static void resetDatabase() {
        Database.resetDatabase();
    }
}
