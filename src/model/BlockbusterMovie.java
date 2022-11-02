package model;

import java.util.ArrayList;
import model.enums.MovieAgeRating;
import model.enums.MovieType;
import model.enums.MovieShowingStatus;

public class BlockbusterMovie extends Movie {
    private MovieType type;
    private double moviePrice;
    public BlockbusterMovie() {
        super();
    }

    public BlockbusterMovie(String UUID, String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus, ArrayList<String> movieCast, String movieDirector, String movieSynopsis,
            double movieDuration, double moviePrice) {
        super(UUID, movieTitle, movieAgeRating, showingStatus, movieCast, movieDirector, movieSynopsis,
                movieDuration);
        this.type = MovieType.Blockbuster;
        this.moviePrice = moviePrice;
    }

    public void setMoviePrice(double price){
        this.moviePrice = price;
    }

    public double getMoviePrice(double price){
        return this.moviePrice;
    }

    public MovieType getMovieType(){
        return this.type;
    }

}