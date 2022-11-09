package src.model;

import java.io.Serializable;
import java.util.ArrayList;

import src.model.enums.*;

/**
 * The Class that handles the data of Movie
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */
public abstract class Movie implements Serializable {

	/**
	 * Unique ID of the movie
	 */
	private String UUID;

	/**
	 * Title of the movie
	 */
	private String movieTitle;

	/**
	 * Showing status of the movie
	 */
	private MovieShowingStatus movieShowingStatus;

	/**
	 * Synopsis of the movie
	 */
	private String movieSynopsis;

	/**
	 * Director of the movie
	 */
	private String movieDirector;

	/**
	 * ArrayList of all the casts of the movie
	 */
	private ArrayList<String> movieCast;

	/**
	 * Overall review rating of the movie
	 */
	private double movieOverallReviewRating;

	/**
	 * ArrayList of all the {@link MovieReview}
	 */
	private ArrayList<MovieReview> movieReviews;

	/**
	 * Age rating of the movie
	 */
	private MovieAgeRating movieAgeRating;

	/**
	 * Duration of the movie
	 */
	private double movieDuration;

	/**
	 * Total number of tickets sold of the movie
	 */
	private int movieTicketsSold;

	/**
	 * For java serializable
	 */
	private final static long serialVersionUID = 8L;

	/**
	 * Constructor for Movie class
	 * 
	 * @param UUID           is the unique ID in the database
	 * @param movieTitle     is the title of the movie
	 * @param movieAgeRating is the age rating of the movie
	 * @param showingStatus  is the showing status of the movie
	 * @param movieCast      is all the casts of the movie
	 * @param movieDirector  is the director of the movie
	 * @param movieSynopsis  is the synopsis of the movie
	 * @param movieDuration  is the duration of the movie
	 */
	public Movie(String UUID, String movieTitle, MovieAgeRating movieAgeRating,
			MovieShowingStatus showingStatus,
			ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration) {

		this.UUID = UUID;
		this.movieTitle = movieTitle;
		this.movieAgeRating = movieAgeRating;
		this.movieShowingStatus = showingStatus;
		this.movieCast = movieCast;
		this.movieDirector = movieDirector;
		this.movieSynopsis = movieSynopsis;
		this.movieDuration = movieDuration;
		this.movieReviews = new ArrayList<>();
		this.movieTicketsSold = 0;
	}

	/**
	 * Gets the unique ID of the Movie
	 * 
	 * @return Unique ID of the Movie
	 */
	public String getUUID() {
		return this.UUID;
	}

	/**
	 * Sets the unique ID of the Movie
	 * 
	 * @param UUID is the unique ID to be set
	 */
	public void setUUID(String UUID) {
		this.UUID = UUID;
	}

	/**
	 * Gets the title of the movie
	 * 
	 * @return title of the movie
	 */
	public String getMovieTitle() {
		return movieTitle;
	}

	/**
	 * Sets the title of the movie
	 * 
	 * @param movieTitle is the title of the movie to be set
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	/**
	 * Gets the showing status of the movie
	 * 
	 * @return {@link MovieShowingStatus} of the movie
	 */
	public MovieShowingStatus getMovieShowingStatus() {
		return movieShowingStatus;
	}

	/**
	 * Sets the showing status of the movie
	 * 
	 * @param movieShowingStatus is the {@link MovieShowingStatus} of the movie to
	 *                           be set
	 */
	public void setMovieShowingStatus(MovieShowingStatus movieShowingStatus) {
		this.movieShowingStatus = movieShowingStatus;
	}

	/**
	 * Gets the synopsis of the movie
	 * 
	 * @return synopsis of the movie
	 */
	public String getMovieSynopsis() {
		return movieSynopsis;
	}

	/**
	 * Sets the synopsis of the movie
	 * 
	 * @param movieSynopsis is the synopsis of the movie to be set
	 */
	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}

	/**
	 * Gets the director of the movie
	 * 
	 * @return director of the movie
	 */
	public String getMovieDirector() {
		return movieDirector;
	}

	/**
	 * Sets the director of the movie
	 * 
	 * @param movieDirector is the director of the movie to be set
	 */
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	/**
	 * Gets all the casts of the movie
	 * 
	 * @return all the casts of the movie
	 */
	public ArrayList<String> getMovieCast() {
		return movieCast;
	}

	/**
	 * Sets all the casts of the movie
	 * 
	 * @param movieCast is ArrayList of casts to be set
	 */
	public void setMovieCast(ArrayList<String> movieCast) {
		this.movieCast = movieCast;
	}

	/**
	 * Gets the overall review rating of the movie
	 * 
	 * @return overall review rating of the movie
	 */
	public double getMovieOverallReviewRating() {
		return movieOverallReviewRating;
	}

	/**
	 * Sets the overall review rating of the movie
	 * 
	 * @param movieOverallReviewRating is the overall review rating of the movie to
	 *                                 be set
	 */
	public void setMovieOverallReviewRating(double movieOverallReviewRating) {
		this.movieOverallReviewRating = movieOverallReviewRating;
	}

	/**
	 * Gets all the reviews of the movie
	 * 
	 * @return all the reviews of the movie
	 */
	public ArrayList<MovieReview> getMovieReviews() {
		return movieReviews;
	}

	/**
	 * Sets all the reviews of the movie
	 * 
	 * @param movieReviews is ArrayList of {@link MovieReview} to be set
	 */
	public void setMovieReviews(ArrayList<MovieReview> movieReviews) {
		this.movieReviews = movieReviews;
	}

	/**
	 * Adds new review to the movie
	 * 
	 * @param {@link MovieReview} is movie review to be added
	 */
	public void addMovieReview(MovieReview movieReview) {
		this.movieReviews.add(movieReview);
	}

	/**
	 * Gets the age rating of the movie
	 * 
	 * @return {@link MovieAgeRating} of the movie
	 */
	public MovieAgeRating getMovieAgeRating() {
		return movieAgeRating;
	}

	/**
	 * Set the age rating of the movie
	 * 
	 * @param {@link MovieAgeRating} is age rating of the movie to be set
	 */
	public void setMovieAgeRating(MovieAgeRating movieAgeRating) {
		this.movieAgeRating = movieAgeRating;
	}

	/**
	 * Gets the duration of the movie
	 * 
	 * @return duration of the movie
	 */
	public double getMovieDuration() {
		return movieDuration;
	}

	/**
	 * Sets the duration of the movie
	 * 
	 * @param movieDuration is the duration of the movie to be set
	 */
	public void setMovieDuration(double movieDuration) {
		this.movieDuration = movieDuration;
	}

	/**
	 * Gets the total number of tickets sold
	 * 
	 * @return total number of tickets sold
	 */
	public int getMovieTicketsSold() {
		return movieTicketsSold;
	}

	/**
	 * Sets the total number of tickets sold
	 * 
	 * @param movieTicketsSold is the total number of tickets sold to be set
	 */
	public void setMovieTicketsSold(int movieTicketsSold) {
		this.movieTicketsSold = movieTicketsSold;
	}

	/**
	 * Gets the price weight of the movie type
	 * 
	 * @return The price weight of the movie type
	 */
	public abstract double getMoviePrice();

	/**
	 * Sets the price weight of the movie type
	 * 
	 * @param price is the price weight of the movie type to be set
	 */
	public abstract void setMoviePrice(double price);

	/**
	 * Gets the price weight of the movie type
	 * 
	 * @return The price weight of the movie type
	 */
	public abstract MovieType getMovieType();

	/**
	 * Sets the movie type of the movie
	 * 
	 * @param movieType is the {@link MovieType} to be set
	 */
	public abstract void setMovieType(MovieType movieType);
}
