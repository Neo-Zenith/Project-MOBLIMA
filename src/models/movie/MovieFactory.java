package models.movie;

import java.util.ArrayList;
import java.util.List;

public class MovieFactory {

    public Movie createMovie(String movieTitle, String movieType, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast,
            String movieDirector, String movieSynopsis, double movieDuration,
            ArrayList<DateTime> showingTime) {
        switch (movieType) {
            case "BlockBuster":
                return new BlockbusterMovie(movieTitle, movieType, movieAgeRating, showingStatus,
                        movieCast, movieDirector, movieSynopsis, movieDuration, showingTime);
            case "ThreeD":
                return new ThreeDMovie(movieTitle, movieType, movieAgeRating, showingStatus,
                        movieCast, movieDirector, movieSynopsis, movieDuration, showingTime);
            case "Standard":
                return new StandardMovie(movieTitle, movieType, movieAgeRating, showingStatus,
                        movieCast, movieDirector, movieSynopsis, movieDuration, showingTime);
            default:
                return new StandardMovie(movieTitle, movieType, movieAgeRating, showingStatus,
                        movieCast, movieDirector, movieSynopsis, movieDuration, showingTime);
        }
    }

}