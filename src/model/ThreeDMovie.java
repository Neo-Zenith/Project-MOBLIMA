package model;

import model.enums.MovieAgeRating;
import model.enums.MovieType;
import model.enums.MovieShowingStatus;
import java.util.ArrayList;

public class ThreeDMovie extends Movie {
    private MovieType type;
    private double moviePrice;

    public ThreeDMovie() {
        super();
    }

    public ThreeDMovie(String UUID, String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration,
            double moviePrice) {
        super(UUID, movieTitle, movieAgeRating, showingStatus, movieCast,
                movieDirector, movieSynopsis, movieDuration);
        this.type = MovieType.ThreeD;
        this.moviePrice = moviePrice;

    }

    @Override
    public void setMoviePrice(double price) {
        this.moviePrice = price;
    }

    @Override
    public double getMoviePrice() {
        return this.moviePrice;
    }

    @Override
    public MovieType getMovieType() {
        return this.type;
    }

    public void setMovieType(MovieType type) {
        this.type = type;
    }
}