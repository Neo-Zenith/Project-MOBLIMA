package models.movie;

public class MovieReview {
    private String review;
    private double movieReviewRating;
    private int movieGoerID;
    private String movieTitle;
    private String movieType;

    public MovieReview(int movieGoerID, String movieTitle, String movieType, String review, double movieReviewRating) {
        this.review = review;
        this.movieReviewRating = movieReviewRating;
        this.movieGoerID = movieGoerID;
        this.movieTitle = movieTitle;
        this.movieType = movieType;
    }

    public String getReview() {
        return this.review;
    }

    public double getMovieReviewRating() {
        return this.movieReviewRating;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public String getMovieType(){
        return this.movieType;
    }

    public int getMovieGoerID() {
        return this.movieGoerID;
    }
}