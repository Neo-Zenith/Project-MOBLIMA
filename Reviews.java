
public class Reviews {
	private String movieTitle;
	private String movieGoerName;
	private String[] review;
	private int[] ratings;
	private int numReviewRatings;
	
	public String getMovieTitle() {
		return movieTitle;
	}
	
	public String movieGoerName() {
		return movieGoerName;
	}
	
	public String[] getReview() {
		return review;
	}
	
	public int[] getRatings() {
		return ratings;
	}
	
	public int numReviewRatings() {
		return numReviewRatings;
	}
	
	public float calculateOverallRating() {
		float overallRating = 0;
		for (int i = 0; i < numReviewRatings; i++) {
			overallRating += ratings[i];
			overallRating = overallRating / numReviewRatings;
		}
		return overallRating;
	}
}

