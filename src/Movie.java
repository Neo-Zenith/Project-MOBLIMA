
public class Movie {
	private String title;
	private MovieType movieType;
	private AgeRating ageRating;
	private float overallRating;
	private ShowingStatus showStatus;
	private String[] cast;
	private String director;
	private String synopsis;
	private int duration;
	
	public Movie(String title, MovieType movieType, AgeRating ageRating, float overallRating, ShowingStatus showStatus, String[] cast, String director, String synopsis, int duration) {
		this.title = title;
		this.movieType = movieType;
		this.ageRating = ageRating;
		this.overallRating = overallRating;
		this.showStatus = showStatus;
		this.cast = cast;
		this.director = director;
		this.synopsis = synopsis;
		this.duration = duration;   
	}
	
	
	
	
	public enum ShowingStatus{
		COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING;
	}
	
	public enum AgeRating{
		G, PG, PG13, NC16, M18, R21;
	}
	
	public String getTitle() {
		return title;
	}
	
	public MovieType getMovieType() {
		return movieType;
	}
	
	public AgeRating getAgeRating() {
		return ageRating;
	}
	
	public float getOverallRating() {
		return overallRating;
	}
	
	public ShowingStatus getShowingStatus() {
		return showStatus;
	}
	
	public String[] getCast() {
		return cast;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public int getDuration() {
		return duration;
	}
	
	
}
