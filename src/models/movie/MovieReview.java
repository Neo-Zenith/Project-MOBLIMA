package models.movie;

public class MovieReview {
    private String review;
    private float movieReviewRating;
    private int movieGoerID;
    private String movieTitle;

    public MovieReview(int movieGoerID, String movieTitle, String review, float movieReviewRating) {
        this.review = review;
        this.movieReviewRating = movieReviewRating;
        this.movieGoerID = movieGoerID;
        this.movieTitle = movieTitle;
    }

    public String getReview() {
        return this.review;
    }

    public float getMovieReviewRating() {
        return this.movieReviewRating;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public int getMovieGoerID() {
        return this.movieGoerID;
    }
}