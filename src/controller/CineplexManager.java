package controller;

import java.util.*;
import model.*;
import model.enums.*;
import database.*;
import handler.*;


public class CineplexManager {
    
    public CineplexManager() {}

    /**
     * Creates a {@Link Cineplex} and save to {@Link Database}
     * @param cineplexName is the name of the {@Link Cineplex}
     * @param numOfCinemas is the number of {@link Cinema} in the cineplex
     * @param cinemas is the ArrayList of {@link Cinema} instances in the cineplex
     * @param cineplexLocation is the location of the cineplex
     * @return {@link Cineplex} instance which was created
     */
    public static Cineplex createCineplex(String cineplexName, int numOfCinemas, ArrayList <Cinema> cinemas, String cineplexLocation) {
        String UUID = String.format("CP%04d", DatabaseHandler.generateUUID(Database.CINEPLEX));
        Cineplex cineplex = new Cineplex(UUID, cineplexName, numOfCinemas, cinemas, cineplexLocation);
        DatabaseManager.saveUpdateToDatabase(UUID, cineplex, Database.CINEPLEX);
        return cineplex;
    }


    /**
     * Counts the number of different cinema classes for a cineplex
     * @param cineplex the target {@link Cineplex}
     * @param cinemaCLass the target {@link CinemaClass}
     * @return the number of cinemas of which belong to {@param cinemaClass}
     */
    public static int countCinemaClass(Cineplex cineplex, CinemaClass cinemaClass) {
        int count = 0;
        ArrayList <Cinema> cinemas = cineplex.getCinemas();

        for (int i = 0; i < cinemas.size(); i ++) {
            if (cinemas.get(i).getCinemaClass() == cinemaClass) {
                count ++;
            }
        }
        return count;
    }


    /**
     * Get the {@Cineplex} instance of which the {@param targetCinema} belongs to
     * @param targetCinema is the {@link Cinema} instance which we want to find its {@Link Cineplex}
     * @return  {@Link Cineplex} instance which contains the {@param targetCinema}
     */
    public static Cineplex getCineplexByCinema(Cinema targetCinema) {
        ArrayList <Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());

        for (int i = 0; i < cineplexes.size(); i ++) {
            Cineplex cineplex = cineplexes.get(i);
            for (int j = 0; j < cineplex.getCinemas().size(); j ++) {
                Cinema cinema = cineplex.getCinemas().get(j);
                if (cinema.getUUID().equals(targetCinema.getUUID())) {
                    return cineplex;
                }
            }
        }

        return null;
    }


    /**
     * Returns a filtered list of cineplexes which contains cinemas that are showing {@param movie}
     * @param movie is the target movie
     * @return ArrayList of {@link Cineplex} instances
     */
    public static ArrayList<Cineplex> filterCineplexesByMovie(Movie movie) {
        ArrayList <Cineplex> cineplexes = new ArrayList<>();
        MovieSchedule movieSchedule = MovieScheduleManager.filterMovieSchedulesByMovie(movie);
        cineplexes.addAll(CineplexManager.filterCineplexesByMovieSchedule(movieSchedule));

        HashSet <Cineplex> cineplexesSet = new HashSet<Cineplex>(cineplexes);
        ArrayList <Cineplex> finalCineplexes = new ArrayList<>(cineplexesSet);
        return finalCineplexes;
    }


    /**
     * Returns a filtered list of cineplexes which contains cinemas that are found in the {@param movieSchedule}
     * @param movieSchedule is the target movie schedule
     * @return ArrayList of {@Link Cineplex} instances
     */
    public static ArrayList <Cineplex> filterCineplexesByMovieSchedule(MovieSchedule movieSchedule) {
        ArrayList <Cineplex> cineplexes = new ArrayList<>();
        ArrayList <Cineplex> allCineplexes = Database.getValueList(Database.CINEPLEX.values());
        ArrayList <Cinema> targetCinemas = movieSchedule.getShowingVenues();

        for (int i = 0; i < allCineplexes.size(); i ++) {
            Cineplex cineplex = allCineplexes.get(i);
            ArrayList <Cinema> cineplexCinemas = cineplex.getCinemas();

            for (int j = 0; j < cineplexCinemas.size(); j ++) {
                Cinema cinema = cineplexCinemas.get(j);

                if (cineplexes.indexOf(cineplex) == -1) {
                    for (int k = 0; k < targetCinemas.size(); k ++) {
                        Cinema targetCinema = targetCinemas.get(k);
                        if (cinema.getUUID().equals(targetCinema.getUUID())) {
                            cineplexes.add(cineplex);
                        }
                    }
                }
            }
        }

        return cineplexes;
    }
}
