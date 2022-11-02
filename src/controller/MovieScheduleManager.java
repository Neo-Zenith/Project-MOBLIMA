package controller;

import model.DateTime;
import model.MovieSchedule;
import model.Seat;
import model.Cinema;
import model.Movie;
import database.Database;
import handler.DatabaseHandler;

import java.util.ArrayList;

public class MovieScheduleManager {
    
    public MovieScheduleManager() {}

    /**
     * Method to instantiate a {@link MovieSchedule} object in the database
     * @param movieOnShow the movie that is to be showed on the showing setting
     * @param showingVenue ArrayList of {@link Cinema} objects of which the showing setting will take place at
     * @param seatingPlan ArrayList of array of {@link Seat} objects that represents the booking state for each movie
     * @param showingTime {@link DateTime} object which tells the time when the movie will play
     * @return {@link MovieSchedule} object that was instantiated
     */
    public static MovieSchedule createMovieSchedule(Movie movieOnShow, ArrayList <Cinema> showingVenue,
                                                ArrayList <ArrayList <Seat>> seatingPlan, ArrayList <DateTime> showingTime) {

        MovieSchedule movieSchedule = updateMovieSchedule(movieOnShow, showingVenue, showingTime); 
        if (movieSchedule != null) {
            return movieSchedule;
        }

        String UUID = String.format("MS%03d", DatabaseHandler.generateUUID(Database.MOVIE_SCHEDULE));
        movieSchedule = new MovieSchedule(UUID, movieOnShow, showingVenue, seatingPlan, showingTime);
        DatabaseManager.saveUpdateToDatabase(UUID, movieSchedule, Database.MOVIE_SCHEDULE);
        return movieSchedule;
    }

    /**
     * Method to update an existing {@link MovieSchedule}
     * @param movieOnShow the movie that is to be showed on the showing setting
     * @param cinema ArrayList of {@link Cinema} which tells the showing venue to be added into schedule
     * @param showingTime ArrayList of {@link DateTime} object which tells the time when the movie will play for each showing venue
     * @return {@code true} if update is successful; {@code false} otherwise
     */
    public static MovieSchedule updateMovieSchedule(Movie movieOnShow, ArrayList <Cinema> cinema, ArrayList <DateTime> showingTime) {
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        for (int i = 0; i < movieSchedules.size(); i ++) {
            MovieSchedule movieSchedule = movieSchedules.get(i);
            if (movieSchedule.getMovieOnShow().getUUID().contains(movieOnShow.getUUID())) {
                for (int j = 0; j < showingTime.size(); j ++) {
                    int index1 = movieSchedule.getShowingVenues().indexOf(cinema.get(j));
                    int index2 = movieSchedule.getShowingTime().indexOf(showingTime.get(j));
                    if (index1 != -1 || index2 != -1) {
                        movieSchedule.getShowingVenues().set(index1, cinema.get(j));
                        movieSchedule.getShowingTime().set(index2, showingTime.get(j));
                    }
                    else {
                        movieSchedule.addShowingTime(showingTime.get(j));
                        movieSchedule.addShowingVenue(cinema.get(j));
                    }
                }
                return movieSchedule;
            }
        }
        return null;
    }

    /**
     * Method to remove an existing {@link MovieSchedule}
     * @param movieSchedule the {@link MovieSchedule} instance to be removed
     * @return {@code true} if removal is successful; {@code false} otherwise
     */
    public static boolean removeMovieSchedule(MovieSchedule movieSchedule) {
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());
        ArrayList <String> movieSchedulesKeyList = Database.getKeyList(Database.MOVIE_SCHEDULE.keySet());

        int index = movieSchedules.indexOf(movieSchedule);
        String UUID = movieSchedulesKeyList.get(index);
        Database.MOVIE_SCHEDULE.remove(UUID);
        return true;
    }


    /**
     * Method to obtain a filterd list of movie schedules based on cinema
     * @param cinema the filter query parameter to filter movie schedules
     * @return ArrayList of {@link MovieSchedule} instances which is showed at {@code cinema}
     */
    public static ArrayList <MovieSchedule> filterMovieSchedules(Cinema cinema) {
        ArrayList <MovieSchedule> filteredSchedules = new ArrayList<>();
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        for (int i = 0; i < movieSchedules.size(); i ++) {
            for (int j = 0; j < movieSchedules.get(i).getShowingVenues().size(); j ++) {
                if (movieSchedules.get(i).getShowingVenues().get(j).getUUID().contains(cinema.getUUID())) {
                    filteredSchedules.add(movieSchedules.get(i));
                }
            }
            
        }

        return filteredSchedules;
    }

    public static ArrayList<MovieSchedule> printMovieSchedule(Cinema cinema) {
        ArrayList<MovieSchedule> filteredSchedules = MovieScheduleManager.filterMovieSchedules(cinema);

        for (int i = 0; i < filteredSchedules.size(); i++) {
            System.out.println(i + 1 + ". ");
            System.out.println("Scheduled Movie: " + filteredSchedules.get(i).getMovieOnShow().getMovieTitle());
            Movie m = filteredSchedules.get(i).getMovieOnShow();
            System.out.println("Movie Title: " + m.getMovieTitle());
            System.out.println("Movie Type: " + m.getMovieType());
            System.out.println("Rated: " + m.getMovieAgeRating());
            System.out.println("Duration: " + m.getMovieDuration());
            System.out.println("Review rating: " + m.getMovieOverallReviewRating());
            System.out.println("Director: " + m.getMovieDirector());
            System.out.print("Cast: ");
            for (int j = 0; j < m.getMovieCast().size(); j++) {
                System.out.print(m.getMovieCast().get(j) + " ");
            }
            System.out.println("");
            System.out.println("Synopsis: " + m.getMovieSynopsis());
            System.out.println("Price: $" + m.getMoviePrice());
            DateTime showingTime = filteredSchedules.get(i).getShowingTime();
            System.out.print("Showing Time: ");
            showingTime.printTime();
        }

        return filteredSchedules;
    }


    public static int getShowingVenueIndex(MovieSchedule movieSchedule, Cinema cinema) {
        for (int i = 0; i < movieSchedule.getShowingVenues().size(); i ++) {
            if (movieSchedule.getShowingVenues().get(i).getUUID().contains(cinema.getUUID())) {
                return i;
            }
        }
        return -1;
    }
}
