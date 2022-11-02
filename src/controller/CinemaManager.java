package controller;

import model.Cineplex;
import model.Cinema;
import model.IMaxCinema;
import model.PlatinumCinema;
import model.Seat;
import model.StandardCinema;
import model.enums.CinemaClass;
import database.Database;
import handler.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;

public class CinemaManager {

    public CinemaManager() {}

    /**
     * Method to instantiate a platinum class cinema
     * @param cinemaClass   {@link CinemaClass}
     * @param cinemaPrice   price weight of the cinema class
     * @param seats     array list of {@link Seat} in the cinema
     */
    public static Cinema createPlatinumCinema(CinemaClass cinemaClass, double cinemaPrice, ArrayList <Seat> seats) {
        String UUID = String.format("CN%04d", DatabaseHandler.generateUUID(Database.CINEMA));
        Cinema cinema = new PlatinumCinema(UUID, cinemaClass, seats, cinemaPrice, Database.platinumNumOfRow, Database.platinumNumOfSeatsPerRow * Database.platinumNumOfRow);
        DatabaseManager.saveUpdateToDatabase(UUID, cinema, Database.CINEMA);
        return cinema;
    }

    /**
     * Method to instantiate a standard class cinema
     * @param cinemaClass   {@link CinemaClass}
     * @param cinemaPrice   price weight of the cinema class
     * @param seats     array list of {@link Seat} in the cinema
     */
    public static Cinema createStandardCinema(CinemaClass cinemaClass, double cinemaPrice, ArrayList <Seat> seats) {
        String UUID = String.format("CN%04d", DatabaseHandler.generateUUID(Database.CINEMA));
        Cinema cinema = new StandardCinema(UUID, cinemaClass, seats, cinemaPrice, Database.numOfRows, Database.totalNumOfSeats);
        DatabaseManager.saveUpdateToDatabase(UUID, cinema, Database.CINEMA);
        return cinema;
    }

    /**
     * Method to instantiate a IMAX class cinema
     * @param cinemaClass   {@link CinemaClass}
     * @param cinemaPrice   price weight of the cinema class
     * @param seats     array list of {@link Seat} in the cinema
     */
    public static Cinema createIMaxCinema(CinemaClass cinemaClass, double cinemaPrice, ArrayList <Seat> seats) {
        String UUID = String.format("CN%04d", DatabaseHandler.generateUUID(Database.CINEMA));
        Cinema cinema = new IMaxCinema(UUID, cinemaClass, seats, cinemaPrice, Database.numOfRows, Database.totalNumOfSeats);
        DatabaseManager.saveUpdateToDatabase(UUID, cinema, Database.CINEMA);
        return cinema;
    }

    /**
     * Method to obtain a filtered list of {@link Cinema} instances of a {@link Cineplex}
     * that fulfills the {@link CinemaClass} requirement
     * @param cinemaClass requirement that the filtered list of {@link Cinema} instances must meet
     * @param cineplex the target cineplex
     * @return ArrayList of {@link Cinema} instances that satisfy the requirement
     */
    public static ArrayList <Cinema> filterCinemaByClass(CinemaClass cinemaClass, Cineplex cineplex) {
        ArrayList <Cinema> filteredCinemas = new ArrayList<>();
        ArrayList <Cinema> cinemas = cineplex.getCinemas();
        
        for (int i = 0; i < cinemas.size(); i ++) {
            Cinema cinema = cinemas.get(i);
            if (cinema.getCinemaClass() == cinemaClass) {
                filteredCinemas.add(cinema);
            }
        }
        return filteredCinemas;
    }


    public static ArrayList <Cinema> printCinemasInfo(Cineplex cineplex) {
        ArrayList <Cinema> cinemas = cineplex.getCinemas();
        Collections.sort(cinemas);

        int index = 1;
        for (int i = 0; i < cinemas.size(); i ++) {
            String key = cinemas.get(i).getUUID();
            System.out.println(index + ".  ");
            System.out.print("Reference ID: " + key + "   Class: ");
            System.out.println(cinemas.get(i).getCinemaClass());
            System.out.println(cinemas.get(i).getTotalNumOfSeats());
            System.out.println("");
            index ++;
            
            
        }

        return cinemas;
    }
}
