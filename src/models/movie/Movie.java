package models.movie;

import models.cinema.Cinema;
import java.util.*;
// movie is to query 

public class Movie {
	public static List <Movie> movies = new ArrayList<>();

	private int movieID;
	private String movieTitle;
	private ShowingStatus showingStatus;
	private String movieSynopsis;
	private String movieDirector;
	private String movieCast[];
	private float movieOverallReviewRating;
	private List <MovieReview> movieReviews;
	private AgeRating movieAgeRating;
	private MovieType movieType;
	private Cinema showingVenue[];
	private DateTime showingTime[];
	private float movieDuration;
	
	public Movie(	int movieID, String movieTitle, MovieType movieType, 
					AgeRating movieAgeRating, ShowingStatus showingStatus, String movieCast[], 
					String movieDirector, String movieSynopsis, float movieDuration, 
					Cinema showingVenue[], DateTime showingTime[]) {

		this.movieID = movieID;
		this.movieTitle = movieTitle;
		this.movieType = movieType;
		this.movieAgeRating = movieAgeRating;
		this.showingStatus = showingStatus;
		this.movieCast = movieCast;
		this.movieDirector = movieDirector;
		this.movieSynopsis = movieSynopsis;
		this.movieDuration = movieDuration;   
		this.showingVenue = showingVenue;
		this.showingTime = showingTime;
		this.movieReviews = new ArrayList<>();
		Movie.movies.add(this);
	}

	public String getMovieTitle() {
		return this.movieTitle;
	}

	public void setMovieTitle(String movieTitle){
		this.movieTitle = movieTitle;
	}
	
	public MovieType getMovieType() {
		return this.movieType;
	}

	public void setMovieType(MovieType movieType){
		this.movieType = movieType;
	}
	
	public AgeRating getMovieAgeRating() {
		return this.movieAgeRating;
	}

	public void setMovieAgeRating(AgeRating movieAgeRating){
		this.movieAgeRating = movieAgeRating;
	} 
	
	public float getMovieOverallReviewRating() {
		return this.movieOverallReviewRating;
	}
	
	public ShowingStatus getShowingStatus() {
		return this.showingStatus;
	}

	public void setShowingStatus(ShowingStatus showingStatus){
		this.showingStatus = showingStatus;
	}
	
	public String[] getMovieCast() {
		return this.movieCast;
	}

	public void setMovieCast(String movieCast, int castNumber){
		this.movieCast[castNumber] = movieCast;
	}
	
	public String getMovieDirector() {
		return this.movieDirector;
	}
	
	public void setMovieDirector(String movieDirector){
		this.movieDirector = movieDirector;
	}

	public String getMovieSynopsis() {
		return this.movieSynopsis;
	}
	
	public void setMovieSynopsis(String movieSynopsis){
		this.movieSynopsis = movieSynopsis;
	}

	public float getMovieDuration() {
		return this.movieDuration;
	}

	public void setMovieDuration(float movieDuration){
		this.movieDuration = movieDuration;
	}

	public DateTime[] getShowingTime() {
		return this.showingTime;
	}

	public void setShowingTime(DateTime showTime, int showingTimeID ){
		this.showingTime[showingTimeID] = showTime;
	}

	public Cinema[] getShowingVenue() {
		return this.showingVenue;
	}
	
	public float calculateOverallReviewRating() {
		if (this.movieReviews.size() == 0) {
			return -1;
		}

		float overallRating = 0;
		for (int i = 0; i < this.movieReviews.size(); i ++) {
			overallRating += this.movieReviews.get(i).getMovieReviewRating();
		}

		return overallRating / this.movieReviews.size();
	}

	public void writeMovieReview(int movieReviewRating, String movieReview, int movieGoerID) {
		int reviewID = this.movieReviews.size() + 1;
		this.movieReviews.add(new MovieReview(	reviewID, movieReviewRating, 
												movieReview, movieGoerID));
	}

	public List <MovieReview> getListOfMovieReviews() {
		return this.movieReviews;
	}

	public void printMovies(List <Movie> movies){
		for (int i = 0; i < movies.size(); i++){
			

		}

	}
}
