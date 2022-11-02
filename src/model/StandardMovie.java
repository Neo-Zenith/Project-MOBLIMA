package model;

import java.util.ArrayList;
import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;

public class StandardMovie extends Movie {

    public StandardMovie() {
        super();
    }

    public StandardMovie(String UUID, String movieTitle, String movieType, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus, ArrayList<String> movieCast, String movieDirector, String movieSynopsis,
            double movieDuration) {
        super(UUID, movieTitle, movieType, movieAgeRating, showingStatus, movieCast,
                movieDirector, movieSynopsis, movieDuration);
    }

}