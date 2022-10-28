public abstract class MovieGoer{
    private String name;
    private int userID;
    private String email;
    private String mobileNum;
    private List <Payment> bookingHistory;
    private List <MovieReview> reviewHistory;

    public MovieGoer(int userID, String name, String email, String mobileNum) {
        super(name, userID);
        this.email = email;
        this.mobileNum = mobileNum;
        this.bookingHistory = new ArrayList<>();
        this.reviewHistory = new ArrayList<>();
    } 

    public String getEmail() {
        return this.email;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public List <MovieReview> getUserMovieReview() {
        return this.reviewHistory;
    }

    public List <Payment> getBookingHistory() {
        return this.bookingHistory;
    }

    public void setUserMovieReview(String movieTitle, String review, float movieReviewRating) {
		MovieReview newReview = new MovieReview(this.userID, movieTitle, review, movieReviewRating);
        this.reviewHistory.add(newReview);
        Movie.writeMovieReview(newReview);
    }

    public abstract float getPrice();

    // method to call the payment method and add it to the booking history
}