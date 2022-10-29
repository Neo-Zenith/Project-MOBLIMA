package models.movie;

import java.util.ArrayList;

public class StandardMovie extends Movie {

    public StandardMovie() {
        super();
    }

    public StandardMovie(String movieTitle, String movieType, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus, ArrayList<String> movieCast, String movieDirector, String movieSynopsis,
            double movieDuration, ArrayList<DateTime> showingTime) {
        super(movieTitle, movieType, movieAgeRating, showingStatus, movieCast, movieDirector, movieSynopsis,
                movieDuration, showingTime);
    }

}