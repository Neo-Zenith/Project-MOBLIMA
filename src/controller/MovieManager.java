package controller;

import java.util.ArrayList;

import model.BlockbusterMovie;
import model.StandardMovie;
import model.ThreeDMovie;
import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.Movie;
import model.MovieReview;
import model.DateTime;
import handler.DatabaseHandler;
import database.Database;

public class MovieManager {

    public Movie createMovie(String movieTitle, String movieType, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast,
            String movieDirector, String movieSynopsis, double movieDuration) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie;
        switch (movieType) {
            case "BlockBuster":
                movie = new BlockbusterMovie(UUID, movieTitle, movieType, movieAgeRating, showingStatus,
                        movieCast, movieDirector, movieSynopsis, movieDuration);
                break;
            case "ThreeD":
                movie = new ThreeDMovie(UUID, movieTitle, movieType, movieAgeRating, showingStatus,
                        movieCast, movieDirector, movieSynopsis, movieDuration);
                break;
            case "Standard":
                movie = new StandardMovie(UUID, movieTitle, movieType, movieAgeRating, showingStatus,
                        movieCast, movieDirector, movieSynopsis, movieDuration);
                break;
            default:
                movie = new StandardMovie(UUID, movieTitle, movieType, movieAgeRating, showingStatus,
                        movieCast, movieDirector, movieSynopsis, movieDuration);
        }
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

}