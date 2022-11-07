package controller;

import model.*;
import database.*;
import handler.*;
import java.util.*;


public class MovieScheduleManager {

    public MovieScheduleManager() {}

    
    public static MovieSchedule createMovieSchedule(String movieUUID, ArrayList <String> showingVenuesUUID, ArrayList <ArrayList <Seat>> seatingPlan, ArrayList <DateTime> showingTime) {

        MovieSchedule movieSchedule = updateMovieSchedule(movieUUID, showingVenuesUUID, showingTime); 
        if (movieSchedule != null) {
            return movieSchedule;
        }

        String UUID = String.format("MS%04d", DatabaseHandler.generateUUID(Database.MOVIE_SCHEDULE));
        movieSchedule = new MovieSchedule(UUID, movieUUID, showingVenuesUUID, seatingPlan, showingTime);
        DatabaseManager.saveUpdateToDatabase(UUID, movieSchedule, Database.MOVIE_SCHEDULE);
        return movieSchedule;
    }

    
    public static MovieSchedule updateMovieSchedule(String movieUUID, ArrayList <String> showingVenuesUUID, ArrayList <DateTime> showingTime) {
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        for (int i = 0; i < movieSchedules.size(); i++) {
            MovieSchedule movieSchedule = movieSchedules.get(i);
            if (movieSchedule.getMovieOnShow().equals(movieUUID)) {
                for (int j = 0; j < showingTime.size(); j ++) {
                    int index1 = movieSchedule.getShowingVenues().indexOf(showingVenuesUUID.get(j));
                    int index2 = movieSchedule.getShowingTime().indexOf(showingTime.get(j));
                    if (index1 != -1 || index2 != -1) {
                        movieSchedule.getShowingVenues().set(index1, showingVenuesUUID.get(j));
                        movieSchedule.getShowingTime().set(index2, showingTime.get(j));
                    }
                    else {

                        movieSchedule.addShowingTime(showingTime.get(j));
                        movieSchedule.addShowingVenue(showingVenuesUUID.get(j));
                    }
                }
                DatabaseManager.saveUpdateToDatabase(movieSchedule.getUUID(), movieSchedule, Database.MOVIE_SCHEDULE);
                return movieSchedule;
            }
        }
        return null;
    }

    
    public static MovieSchedule getMovieScheduleByMovie(Movie movie) {
        ArrayList<MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        for (int i = 0; i < movieSchedules.size(); i++) {
            MovieSchedule movieSchedule = movieSchedules.get(i);
            String movieUUID = movieSchedule.getMovieOnShow();

            if (movie.getUUID().equals(movieUUID)) {
                return movieSchedule;
            }
        }
        return null;
    }


    public static int getShowingVenueIndex(MovieSchedule movieSchedule, Cinema cinema) {
        for (int i = 0; i < movieSchedule.getShowingVenues().size(); i++) {
            if (movieSchedule.getShowingVenues().get(i).equals(cinema.getUUID())) {
                return i;
            }
        }
        return -1;
    }


    public static void resetMovieSchedule(MovieSchedule movieSchedule) {
        String movieScheduleUUID = movieSchedule.getUUID();
        ArrayList <String> resetShowingVenues = new ArrayList<>();
        ArrayList <ArrayList<Seat>> resetSeat = new ArrayList<>();
        ArrayList <DateTime> resetShowingTime = new ArrayList<>();
        movieSchedule.setSeatingPlan(resetSeat);
        movieSchedule.setShowingTime(resetShowingTime);
        movieSchedule.setShowingVenues(resetShowingVenues); 

        DatabaseManager.saveUpdateToDatabase(movieScheduleUUID, movieSchedule, Database.MOVIE_SCHEDULE);
    }
}
