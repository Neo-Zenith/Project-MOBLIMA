package controller;

import model.*;
import model.enums.*;
import database.*;
import handler.*;
import java.util.ArrayList;


public class CinemaManager {

    public CinemaManager() {}

    /**
     * Method to create a {@link PlatinumCinema} instance and save to {@link Database}
     * @param seats is the {@link Seat} instances within the {@link Cinema}
     * @return {@link Cinema} instance which was created
     */
    public static Cinema createPlatinumCinema(ArrayList <Seat> seats) {
        String UUID = String.format("CN%04d", DatabaseHandler.generateUUID(Database.CINEMA));
        Cinema cinema = new PlatinumCinema(UUID, seats, Database.platinumNumOfRow, Database.platinumNumOfSeatsPerRow * Database.platinumNumOfRow);
        DatabaseManager.saveUpdateToDatabase(UUID, cinema, Database.CINEMA);
        return cinema;
    }


    /**
     * Method to create a {@link StandardCinema} instance and save to {@link Database}
     * @param seats is the {@link Seat} instances within the {@link Cinema}
     * @return {@link Cinema} instance which was created
     */
    public static Cinema createStandardCinema(ArrayList <Seat> seats) {
        String UUID = String.format("CN%04d", DatabaseHandler.generateUUID(Database.CINEMA));
        Cinema cinema = new StandardCinema(UUID, seats, Database.numOfRows, Database.totalNumOfSeats);
        DatabaseManager.saveUpdateToDatabase(UUID, cinema, Database.CINEMA);
        return cinema;
    }

    
    /**
     * Method to create a {@link IMaxCinema} instance and save to {@link Database}
     * @param seats is the {@link Seat} instances within the {@link Cinema}
     * @return {@link Cinema} instance which was created
     */
    public static Cinema createIMaxCinema(ArrayList <Seat> seats) {
        String UUID = String.format("CN%04d", DatabaseHandler.generateUUID(Database.CINEMA));
        Cinema cinema = new IMaxCinema(UUID, seats, Database.numOfRows, Database.totalNumOfSeats);
        DatabaseManager.saveUpdateToDatabase(UUID, cinema, Database.CINEMA);
        return cinema;
    }


    /**
     * Method to obtain a filtered list of {@link Cinema} instances of a {@link Cineplex}
     * that fulfills the {@link CinemaClass} requirement
     * @param cinemaClass is the requirement that the filtered list of {@link Cinema} instances must meet
     * @param cineplex is the the target cineplex
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


    /**
     * Returns a filtered list of {@link Cinema} which are in {@code cineplex} and are showing
     * {@code movie}
     * @param cineplex  is the {@link Cineplex} which the filtered {@link Cinema} must be in
     * @param movie     is the {@link Movie} which the filtered {@link Cinema} must show
     * @return          ArrayList of {@link Cinema} instances which satisfies the filter requirements
     */
    public static ArrayList <Cinema> filterCinemaByCineplexMovie(Cineplex cineplex, Movie movie) {
        ArrayList <Cinema> filteredCinemas = new ArrayList<>();
        ArrayList <Cinema> cineplexCinemas = cineplex.getCinemas();
        MovieSchedule movieSchedule = MovieScheduleManager.filterMovieSchedulesByMovie(movie);
        ArrayList <Cinema> showingVenues = movieSchedule.getShowingVenues();
            
        for (int i = 0; i < cineplexCinemas.size(); i ++) {
            Cinema cineplexCinema = cineplexCinemas.get(i);
            for (int j = 0; j < showingVenues.size(); j ++) {
                Cinema showingVenue = showingVenues.get(j);
                if (cineplexCinema.getUUID().equals(showingVenue.getUUID())) {
                    filteredCinemas.add(cineplexCinema);
                }
            }
        }
        return filteredCinemas;
    }
}
