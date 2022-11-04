package controller;

import java.util.*;
import model.*;
import model.enums.*;
import handler.*;
import database.*;


public class MovieManager {

    public MovieManager() {}

    /**
     * Creates a {@link StandardMovie} instance and save to {@link Database}
     * @param movieTitle is the title of the movie
     * @param movieAgeRating is the {@link MovieAgeRating} of the movie
     * @param showingStatus is the {@link MovieShowingStatus} of the movie
     * @param movieCast is the list of movie casts
     * @param movieDirector is the movie's director
     * @param movieSynopsis is the movie's plot synopsis
     * @param movieDuration is the duration of the movie
     * @return {@link Movie} isntance which was created
     */
    public static Movie createStandardMovie(String movieTitle, MovieAgeRating movieAgeRating,
                                            MovieShowingStatus showingStatus,
                                            ArrayList<String> movieCast, String movieDirector, 
                                            String movieSynopsis, double movieDuration) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new StandardMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }


    /**
     * Creates a {@link BlockbusterMovie} instance and save to {@link Database}
     * @param movieTitle is the title of the movie
     * @param movieAgeRating is the {@link MovieAgeRating} of the movie
     * @param showingStatus is the {@link MovieShowingStatus} of the movie
     * @param movieCast is the list of movie casts
     * @param movieDirector is the movie's director
     * @param movieSynopsis is the movie's plot synopsis
     * @param movieDuration is the duration of the movie
     * @return {@link Movie} isntance which was created
     */
    public static Movie createBlockbusterMovie(String movieTitle, MovieAgeRating movieAgeRating,
                                                MovieShowingStatus showingStatus,
                                                ArrayList<String> movieCast, String movieDirector, 
                                                String movieSynopsis, double movieDuration) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new BlockbusterMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }


    /**
     * Creates a {@link ThreeDMovie} instance and save to {@link Database}
     * @param movieTitle is the title of the movie
     * @param movieAgeRating is the {@link MovieAgeRating} of the movie
     * @param showingStatus is the {@link MovieShowingStatus} of the movie
     * @param movieCast is the list of movie casts
     * @param movieDirector is the movie's director
     * @param movieSynopsis is the movie's plot synopsis
     * @param movieDuration is the duration of the movie
     * @return {@link Movie} isntance which was created
     */
    public static Movie createThreeDMovie(String movieTitle, MovieAgeRating movieAgeRating,
                                            MovieShowingStatus showingStatus,
                                            ArrayList<String> movieCast, String movieDirector, 
                                            String movieSynopsis, double movieDuration) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new ThreeDMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }


    /**
     * Updates the movie ticket sold for a movie
     * @param movie is the target movie
     */
    public static void updateMovieTicketSold(Movie movie) {
        int ticket = movie.getMovieTicketsSold();
        ticket ++;
        movie.setMovieTicketsSold(ticket);
        String UUID = movie.getUUID();
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
    }


    /**
     * Calculates the overall review rating of a movie
     * @param movie is the target movie
     */
    public static void calculateOverallReviewRating(Movie movie) {
        if (movie.getMovieReviews().size() == 0) {
            movie.setMovieOverallReviewRating(0.0);
            return;
        }

        double overallRating = 0;
        for (int i = 0; i < movie.getMovieReviews().size(); i++) {
            overallRating += movie.getMovieReviews().get(i).getMovieReviewRating();
        }
        movie.setMovieOverallReviewRating(overallRating / movie.getMovieReviews().size());
        String UUID = movie.getUUID();
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
    }

    
    /**
     * Returns all the movies which are in the database
     * @return ArrayList of {@link Movie} instances
     */
    public static ArrayList <Movie> getAllMovieList() {
        return Database.getValueList(Database.MOVIE.values());
    }


    /**
     * Returns a filtered list of {@link Movie} instances which has the name {@param movieTitle}
     * @param movieTitle is the title of the target movies
     * @return ArrayList of {@link Movie} instances which meet the requirement
     */
    public static ArrayList <Movie> getMovieList(String movieTitle) {
        ArrayList <Movie> movies = new ArrayList<>();
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        for (int i = 0; i < movieSchedules.size(); i++) {
            MovieSchedule movieSchedule = movieSchedules.get(i);
            Movie movie = movieSchedule.getMovieOnShow();
            if (movie.getMovieTitle().equals(movieTitle)) {
                movies.add(movie);
            }
        }
        return movies;
    }

    
    /**
     * Sort the movie by {@param sortBy} key
     * @param sortBy is the key to sort the movies
     */
    public static void sortMovie(String sortBy) {
        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());

        for (int i = 1; i < movies.size(); i ++) {
            for (int j = i; j > 0; j --) {
                Movie movie1 = movies.get(j);
                Movie movie2 = movies.get(j - 1);
                if (sortBy.equals("ratings")) {
                    if (movie1.getMovieOverallReviewRating() > movie2.getMovieOverallReviewRating()) {
                        Collections.swap(movies, j, j - 1);
                    }
                    else {
                        break;
                    }
                }
                else {
                    if (movie1.getMovieTicketsSold() > movie2.getMovieTicketsSold()) {
                        Collections.swap(movies, j, j - 1);
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }
}