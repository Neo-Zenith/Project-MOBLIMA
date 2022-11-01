package models.movie;

import java.util.ArrayList;
import java.util.List;

public abstract class Movie {
	public static List<Movie> movies = new ArrayList<>();
	private String movieTitle;
	private String movieType;
	private MovieShowingStatus movieShowingStatus;
	private String movieSynopsis;
	private String movieDirector;
	private ArrayList<String> movieCast;
	private double movieOverallReviewRating;
	private ArrayList<MovieReview> movieReviews;
	private MovieAgeRating movieAgeRating;
	// private ArrayList<Cinema> showingVenue;
	private ArrayList<DateTime> showingTime;
	private double movieDuration;
	private int movieTicketsSold;
	protected double moviePrice;

	public Movie() {
		movies.add(this);
	}

	public Movie(String movieTitle, String movieType, MovieAgeRating movieAgeRating, MovieShowingStatus showingStatus,
			ArrayList<String> movieCast,
			String movieDirector, String movieSynopsis, double movieDuration,
			ArrayList<DateTime> showingTime) {

		this.movieTitle = movieTitle;
		this.movieType = movieType;
		this.movieAgeRating = movieAgeRating;
		this.movieShowingStatus = showingStatus;
		this.movieCast = movieCast;
		this.movieDirector = movieDirector;
		this.movieSynopsis = movieSynopsis;
		this.movieDuration = movieDuration;
		this.movieReviews = new ArrayList<>();
		// this.showingVenue = showingVenue;
		this.showingTime = showingTime;
		this.movieTicketsSold = 0;
		movies.add(this);
	}

	public String getMovieTitle() {
		return this.movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public MovieAgeRating getMovieAgeRating() {
		return this.movieAgeRating;
	}

	public void setMovieAgeRating(MovieAgeRating movieAgeRating) {
		this.movieAgeRating = movieAgeRating;
	}

	public String getMovieType() {
		return this.movieType;
	}

	public double getMovieOverallReviewRating() {
		this.calculateOverallReviewRating();
		return this.movieOverallReviewRating;
	}

	public MovieShowingStatus getShowingStatus() {
		return this.movieShowingStatus;
	}

	public void setShowingStatus(MovieShowingStatus showingStatus) {
		this.movieShowingStatus = showingStatus;
	}

	public ArrayList<String> getMovieCast() {
		return this.movieCast;
	}

	public void setMovieCast(String movieCast, int castNumber) {
		this.movieCast.set(castNumber - 1, movieCast);
	}

	public String getMovieDirector() {
		return this.movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public ArrayList<MovieReview> getMovieReview() {
		return this.movieReviews;
	}

	public String getMovieSynopsis() {
		return this.movieSynopsis;
	}

	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}

	public double getMovieDuration() {
		return this.movieDuration;
	}

	public void setMovieDuration(float movieDuration) {
		this.movieDuration = movieDuration;
	}

	public ArrayList<DateTime> getShowingTime() {
		return this.showingTime;
	}

	public void setShowingTime(DateTime showTime, int showingTimeID) {
		this.showingTime.set(showingTimeID - 1, showTime);
	}
	// public ArrayList<Cinema> getShowingVenue() {
	// return this.showingVenue;
	// }

	// public void setShowingVenue(Cinema venue, int cinemaID) {
	// this.showingVenue.set(cinemaID-1, venue);
	// }

	public void calculateOverallReviewRating() {
		if (this.movieReviews.size() == 0) {
			this.movieOverallReviewRating = 0.0;
			return;
		}

		double overallRating = 0;
		for (int i = 0; i < this.movieReviews.size(); i++) {
			overallRating += this.movieReviews.get(i).getMovieReviewRating();
		}
		this.movieOverallReviewRating = overallRating / this.movieReviews.size();
	}

	public void writeMovieReview(MovieReview newReview) {
		this.movieReviews.add(newReview);
	}

	public ArrayList<MovieReview> getMovieReviews() {
		return this.movieReviews;
	}

	public void printShowingTime() {
		DateTime time;
		System.out.println("Showing Time: ");
		for (int i = 0; i < this.getShowingTime().size(); i++) {
			time = this.getShowingTime().get(i);
			System.out.print((i + 1) + ": ");
			time.printTime();
		}
	}

	public int getMovieTicketsSold(){
		return this.movieTicketsSold;
	}

	public void movieTicketSold(){
		this.movieTicketsSold++;
	}

	public abstract void setMoviePrice(double defaultPrice, double discount, boolean onDiscount);

	public double getMoviePrice(){
		return this.moviePrice;
	}

	public void printMovies() {
		Movie m;
		for (int i = 0; i < this.movies.size(); i++) {
			m = movies.get(i);
			System.out.println("Movie Title: " + m.movieTitle + " (" + m.movieShowingStatus + ")");
			System.out.println("Movie Type: " + m.movieType);
			System.out.println("Rated: " + m.movieAgeRating);
			System.out.println("Duration: " + m.movieDuration);
			System.out.println("Review rating: " + m.getMovieOverallReviewRating());
			System.out.println("Director: " + m.movieDirector);
			System.out.print("Cast: ");
			for (int j = 0; j < m.movieCast.size(); j++) {
				System.out.print(m.movieCast.get(j) + " ");
			}
			System.out.println("");
			System.out.println("Synopsis: " + m.movieSynopsis);
			// System.out.print("Showing venues: ");
			// for(int j=0; j<m.showingVenue.size(); j++){
			// System.out.print(m.showingVenue.get(j).getCinemaID() + " ");
			// }
			System.out.println("Price: $" + m.getMoviePrice());
			System.out.println("Showing time: ");
			for (int j = 0; j < m.showingTime.size(); j++) {
				System.out.print(m.showingTime.get(j).getMinute() + " min ");
				System.out.print(m.showingTime.get(j).getHour() + " hr ");
				System.out.print(m.showingTime.get(j).getDay() + " day ");
				System.out.print(m.showingTime.get(j).getDate() + "/");
				System.out.print(m.showingTime.get(j).getMonth() + "/");
				System.out.println(m.showingTime.get(j).getYear());
			}
			System.out.println("");
		}
	}
}
