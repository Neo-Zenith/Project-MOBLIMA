package model;

import java.io.Serializable;

public class MovieReview implements Serializable {
    private String UUID;
    private String review;
    private double movieReviewRating;
    private String goerUUID;
    private String movieTitle;
    private String movieType;
    private final static long serialVersionUID = 10L;

    public MovieReview(String UUID, String goerUUID, String movieTitle, String movieType, String review,
            double movieReviewRating) {
        this.review = review;
        this.movieReviewRating = movieReviewRating;
        this.goerUUID = goerUUID;
        this.movieTitle = movieTitle;
        this.movieType = movieType;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getMovieReviewRating() {
        return movieReviewRating;
    }

    public void setMovieReviewRating(double movieReviewRating) {
        this.movieReviewRating = movieReviewRating;
    }

    public String getGoerUUID() {
        return goerUUID;
    }

    public void setGoerUUID(String goerUUID) {
        this.goerUUID = goerUUID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

}