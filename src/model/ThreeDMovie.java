package model;

import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import java.util.ArrayList;

public class ThreeDMovie extends Movie {

    public ThreeDMovie() {
        super();
    }

    public ThreeDMovie(String UUID, String movieTitle, String movieType, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration) {
        super(UUID, movieTitle, movieType, movieAgeRating, showingStatus, movieCast,
                movieDirector, movieSynopsis, movieDuration);

    }

}