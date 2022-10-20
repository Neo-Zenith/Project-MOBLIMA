package models.movie;

public class MovieReview {
    private int reviewID;
    private int movieReviewRating;
    private String movieReview;
    private int movieGoerID;

    public MovieReview( int reviewID, int movieReviewRating, 
                        String movieReview, int movieGoerID) {

        this.reviewID = reviewID;
        this.movieReviewRating = movieReviewRating;
        this.movieReview = movieReview;
        this.movieGoerID = movieGoerID;
    }

    public int getMovieReviewRating() {
        return this.movieReviewRating;
    }

    public String getMovieReview() {
        return this.movieReview;
    }

    public int getMovieGoerID() {
        return this.movieGoerID;
    }
}
