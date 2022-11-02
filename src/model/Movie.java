package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;

public abstract class Movie implements Serializable {
	public static ArrayList<Movie> movies = new ArrayList<>();
	private String UUID;
	private String movieTitle;
	private String movieType;
	private MovieShowingStatus movieShowingStatus;
	private String movieSynopsis;
	private String movieDirector;
	private ArrayList<String> movieCast;
	private double movieOverallReviewRating;
	private ArrayList<MovieReview> movieReviews;
	private MovieAgeRating movieAgeRating;
	private double movieDuration;
	private int movieTicketsSold;
	protected double moviePrice;
	private final static long serialVersionUID = 8L;

	public Movie() {
		movies.add(this);
	}

	public Movie(String UUID, String movieTitle, String movieType, MovieAgeRating movieAgeRating,
			MovieShowingStatus showingStatus,
			ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration) {
		
		this.UUID = UUID;
		this.movieTitle = movieTitle;
		this.movieType = movieType;
		this.movieAgeRating = movieAgeRating;
		this.movieShowingStatus = showingStatus;
		this.movieCast = movieCast;
		this.movieDirector = movieDirector;
		this.movieSynopsis = movieSynopsis;
		this.movieDuration = movieDuration;
		this.movieReviews = new ArrayList<>();
		this.movieTicketsSold = 0;
		movies.add(this);
	}

	public String getUUID() {
		return this.UUID;
	}

	public void setUUID(String UUID) {
		this.UUID = UUID;
	}

	public static ArrayList<Movie> getMovies() {
		return movies;
	}

	public static void setMovies(ArrayList<Movie> movies) {
		Movie.movies = movies;
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

	public MovieShowingStatus getMovieShowingStatus() {
		return movieShowingStatus;
	}

	public void setMovieShowingStatus(MovieShowingStatus movieShowingStatus) {
		this.movieShowingStatus = movieShowingStatus;
	}

	public String getMovieSynopsis() {
		return movieSynopsis;
	}

	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public ArrayList<String> getMovieCast() {
		return movieCast;
	}

	public void setMovieCast(ArrayList<String> movieCast) {
		this.movieCast = movieCast;
	}

	public double getMovieOverallReviewRating() {
		return movieOverallReviewRating;
	}

	public void setMovieOverallReviewRating(double movieOverallReviewRating) {
		this.movieOverallReviewRating = movieOverallReviewRating;
	}

	public ArrayList<MovieReview> getMovieReviews() {
		return movieReviews;
	}

	public void setMovieReviews(ArrayList<MovieReview> movieReviews) {
		this.movieReviews = movieReviews;
	}

	public MovieAgeRating getMovieAgeRating() {
		return movieAgeRating;
	}

	public void setMovieAgeRating(MovieAgeRating movieAgeRating) {
		this.movieAgeRating = movieAgeRating;
	}

	public double getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(double movieDuration) {
		this.movieDuration = movieDuration;
	}

	public int getMovieTicketsSold() {
		return movieTicketsSold;
	}

	public void setMovieTicketsSold(int movieTicketsSold) {
		this.movieTicketsSold = movieTicketsSold;
	}

	public double getMoviePrice() {
		return moviePrice;
	}

	public void setMoviePrice(double moviePrice) {
		this.moviePrice = moviePrice;
	}

}
