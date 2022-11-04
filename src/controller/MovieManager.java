package controller;

import java.util.ArrayList;

import model.BlockbusterMovie;
import model.Cinema;
import model.Cineplex;
import model.StandardMovie;
import model.ThreeDMovie;
import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.Movie;
import model.MovieReview;
import model.DateTime;
import handler.DatabaseHandler;
import database.Database;
import model.MovieSchedule;

public class MovieManager {

    public static Movie createStandardMovie(String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration,
            double moviePrice) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new StandardMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration, moviePrice);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }

    public static Movie createBlockbusterMovie(String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration,
            double moviePrice) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new BlockbusterMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration, moviePrice);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }

    public static Movie createThreeDMovie(String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration,
            double moviePrice) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new ThreeDMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration, moviePrice);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }

    public static void updateMovieTicketSold(Movie movie) {
        int ticket = movie.getMovieTicketsSold();
        ticket++;
        movie.setMovieTicketsSold(ticket);
    }

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
    }


    public static void writeMovieReview(Movie movie, MovieReview newReview) {
        movie.getMovieReviews().add(newReview);
    }


    public static ArrayList <Movie> getAllMovieList() {
        return Database.getValueList(Database.MOVIE.values());
    }

    public static ArrayList <Movie> getMovieList(Movie movie) {
        ArrayList <Movie> movies = new ArrayList<>();
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        for (int i = 0; i < movieSchedules.size(); i++) {
            MovieSchedule movieSchedule = movieSchedules.get(i);
            Movie _movie = movieSchedule.getMovieOnShow();
            if (_movie.getMovieTitle().equals(movie.getMovieTitle())) {
                movies.add(_movie);
            }
        }
        return movies;
    }
}