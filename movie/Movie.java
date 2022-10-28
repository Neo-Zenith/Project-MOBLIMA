public abstract class Movie {
	public static List <Movie> movies = new ArrayList<>();

	private int movieID;
	private String movieTitle;
	private MovieShowingStatus movieShowingStatus;
	private String movieSynopsis;
	private String movieDirector;
	private ArrayList<String> movieCast;
	private double movieOverallReviewRating;
	private List <MovieReview> movieReviews;
	private MovieAgeRating movieAgeRating;
	private MovieType movieType;
	private ArrayList<Cinema> showingVenue;
	private ArrayList<DateTime> showingTime;
	private double movieDuration;
	
	public Movie(	int movieID, String movieTitle, MovieType movieType, 
					AgeRating movieAgeRating, ShowingStatus showingStatus, ArrayList<String> movieCast, 
					String movieDirector, String movieSynopsis, double movieDuration, 
					ArrayList<Cinema> showingVenue, ArrayList<DateTime> showingTime) {

		this.movieID = movieID;
		this.movieTitle = movieTitle;
		this.movieType = movieType;
		this.movieAgeRating = movieAgeRating;
		this.movieShowingStatus = showingStatus;
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
		this.movieShowingStatus = showingStatus;
	}
	
	public ArrayList<String> getMovieCast() {
		return this.movieCast;
	}

	public void setMovieCast(String movieCast, int castNumber){
		this.movieCast.set(castNumber-1, movieCast);
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

	public ArrayList<DateTime> getShowingTime() {
		return this.showingTime;
	}

	public void setShowingTime(DateTime showTime, int showingTimeID ){
		this.showingTime.set(showingTimeID-1, showTime);
	}

	public ArrayList<Cinema> getShowingVenue() {
		return this.showingVenue;
	}
	public void setShowingVenue(Cinema venue, int cinemaID) {
		this.showingVenue.set(cinemaID-1, venue);
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

	public void writeMovieReview(MovieReview newReview) {
        String movieName = newReview.getMovieTitle();
        for (int i=0; i<movies.size(); i++){
            if(movieName == movies.get(i).getMovieTitle()){
                movies.get(i).movieReviews.add(newReview);
            }
        }
	}

	public List <MovieReview> getListOfMovieReviews() {
		return this.movieReviews;
	}

	public void printMovies(List <Movie> movies){
		Movie m;
		for (int i = 0; i < movies.size(); i++){
			m = movies.get(i);
			System.out.println("ID: " + m.movieID + " - " + m.movieTitle + " (" + m.showingStatus + ")");
			System.out.println("Movie Type: " + m.movieType.getMovieType() + " Rated: " + m.movieAgeRating);
			System.out.println("Duration: " + m.movieDuration + " Review rating: " + m.movieOverallReviewRating);
			System.out.println("Director: " + m.movieDirector);
			System.out.print("Cast: ");
			for(int j=0; j<m.movieCast.size(); j++){
				System.out.print(m.movieCast.get(j) + " ");
			}
			System.out.println("");
			System.out.println("Synopsis: " + m.movieSynopsis);
			System.out.print("Showing venues: ");
			for(int j=0; j<m.showingVenue.size(); j++){
				System.out.print(m.showingVenue.get(j).getCinemaID() + " ");
			}
			System.out.println("");
			System.out.print("Showing time: ");
			for(int j=0; j<m.showingTime.size(); j++){
				System.out.print(m.showingTime.get(j).getMinute() + " min ");
				System.out.print(m.showingTime.get(j).getHour() + " hr ");
				System.out.print(m.showingTime.get(j).getDay() + " day ");
				System.out.print(m.showingTime.get(j).getDate() + "/");
				System.out.print(m.showingTime.get(j).getMonth() + "/");
				System.out.println(m.showingTime.get(j).getYear());
			}

		}
	}
}
