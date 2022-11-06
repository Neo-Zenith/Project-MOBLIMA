package controller;

import database.Database;
import handler.*;
import model.*;


public class MovieReviewManager {
    
    public MovieReviewManager() {}

    /**
     * Creates a {@link MovieReview} instance and save to {@Link Database}
     * @param movieGoer is the movie goer who writes the review
     * @param movie is the movie the review belongs to
     * @param review is the review description
     * @param movieReviewRating is the rating
     * @return {@MovieReview} instance created
     */
    public MovieReview createMovieReview(MovieGoer movieGoer, Movie movie, String review, double movieReviewRating) {
        String UUID = String.format("MR%04d", DatabaseHandler.generateUUID(Database.MOVIE_REVIEW));
        String goerUUID = movieGoer.getUUID();
        MovieReview movieReview = new MovieReview(UUID, goerUUID, movie, review, movieReviewRating);
        DatabaseManager.saveUpdateToDatabase(UUID, movieReview, Database.MOVIE_REVIEW);

        movie.addMovieReview(movieReview);
        MovieManager.calculateOverallReviewRating(movie);
        DatabaseManager.saveUpdateToDatabase(movie.getUUID(), movie, Database.MOVIE);
        movieGoer.addReviewHistory(movieReview);
        DatabaseManager.saveUpdateToDatabase(goerUUID, movieGoer, Database.MOVIE_GOER);

        return movieReview;
    }
}
