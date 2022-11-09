package src.model;

import java.io.Serializable;

/**
 * The Class that handles the data of Movie Review
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */

public class MovieReview implements Serializable {
    /**
     * Unique ID of the movie review
     */
    private String UUID;

    /**
     * Review of the movie set by the Movie Goer
     */
    private String review;

    /**
     * Rating of the movie
     */
    private double movieReviewRating;

    /**
     * Unique UUID of the Movie Goer that sets this review
     */
    private String goerUUID;

    /**
     * {@link Movie} of the review
     */
    private Movie movie;

    /**
     * For Java Serializable
     */
    private final static long serialVersionUID = 10L;

    /**
     * Constructor for MovieReview class
     * 
     * @param UUID              is the unique ID in the database
     * @param goerUUID          is the unique ID of Movie Goer who sets this review
     * @param movie             is the {@link Movie} of the review
     * @param review            is the review of the movie
     * @param movieReviewRating is the rating of the movie by Movie Goer
     */
    public MovieReview(String UUID, String goerUUID, Movie movie, String review, double movieReviewRating) {
        this.review = review;
        this.movieReviewRating = movieReviewRating;
        this.goerUUID = goerUUID;
        this.movie = movie;
    }

    /**
     * Gets the unique ID of the movie review
     * 
     * @return unique ID of the movie review
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Sets the unique ID of the movie review
     * 
     * @param UUID is the unique ID of the movie review to be set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the review of the movie of the Movie Goer
     * 
     * @return review of the movie by the Movie Goer
     */
    public String getReview() {
        return this.review;
    }

    /**
     * Sets the review of the Movie Goer
     * 
     * @param review is the review of the Movie Goer to be set
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Gets the rating set by the Movie Goer for the movie
     * 
     * @return Rating of the movie set by the Movie Goer
     */
    public double getMovieReviewRating() {
        return movieReviewRating;
    }

    /**
     * Sets the rating of the movie by Movie Goer
     * 
     * @param movieReviewRating is the rating of movie to be set by Movie Goer
     */
    public void setMovieReviewRating(double movieReviewRating) {
        this.movieReviewRating = movieReviewRating;
    }

    /**
     * Gets the unique ID of Movie Goer who sets this review
     * 
     * @return the unique ID of the Movie Goer who sets the review
     */
    public String getGoerUUID() {
        return goerUUID;
    }

    /**
     * Sets the unique ID of Movie Goer who gives this review
     * 
     * @param goerUUID is the unique ID of Movie Goer to be set
     */
    public void setGoerUUID(String goerUUID) {
        this.goerUUID = goerUUID;
    }

    /**
     * Gets the {@link Movie} of the review
     * 
     * @return {@link Movie} of the review
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * Sets the {@link Movie} of the review
     * 
     * @param movie is the {@link Movie} to be set
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}