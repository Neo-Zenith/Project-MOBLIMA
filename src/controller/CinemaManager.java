package controller;

import model.Cinema;
import model.PlatinumCinema;
import model.Seat;
import model.enums.CinemaClass;
import database.Database;
import handler.DatabaseHandler;

import java.util.ArrayList;

public class CinemaManager {

    public CinemaManager() {}

    /**
     * Method to instantiate a platinum class cinema
     * @param cinemaClass   {@link CinemaClass}
     * @param cinemaPrice   price weight of the cinema class
     * @param seats     array list of {@link Seat} in the cinema
     */
    public static Cinema createPlatinumCinema(CinemaClass cinemaClass, double cinemaPrice, ArrayList <Seat> seats) {
        String UUID = String.format("PC%03d", DatabaseHandler.generateUUID(Database.CINEMA));
        Cinema cinema = new PlatinumCinema(UUID, cinemaClass, seats, cinemaPrice, Database.numOfRows, Database.totalNumOfSeats);
        DatabaseManager.saveUpdateToDatabase(UUID, cinema, Database.CINEMA);
        return cinema;
    }
}
