package src.model;

import src.model.enums.*;
import java.util.*;
import src.database.*;

/**
 * The Class that handles the data of Three D movie
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */
public class ThreeDMovie extends Movie {
    /**
     * {@link MovieType} of movie
     */
    private MovieType type;

    /**
     * Price weight of the movie
     */
    private double moviePrice;

    /**
     * Constructor for ThreeD movie
     * 
     * @param UUID           is the unique ID in the database
     * @param movieTitle     is the movie title
     * @param movieAgeRating is the age rating for the movie
     * @param showingStatus  is the showing status of the movie
     * @param movieCast      is all the casts of the movie
     * @param movieDirector  is the director of the movie
     * @param movieSynopsis  is the synopsis of the movie
     * @param movieDuration  is the duration of the movie
     */
    public ThreeDMovie(String UUID, String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus, ArrayList<String> movieCast, String movieDirector,
            String movieSynopsis, double movieDuration) {
        super(UUID, movieTitle, movieAgeRating, showingStatus, movieCast,
                movieDirector, movieSynopsis, movieDuration);
        this.type = MovieType.ThreeD;
        this.moviePrice = Database.PRICES.getDefault3DMoviePrice();
    }

    /**
     * Sets the price weight of the movie type
     * 
     * @param price is the price weight of the movie type
     */
    @Override
    public void setMoviePrice(double price) {
        this.moviePrice = price;
    }

    /**
     * Gets the price weight of the movie type
     * 
     * @return The price weight of the movie type
     */
    @Override
    public double getMoviePrice() {
        return this.moviePrice;
    }

    /**
     * Gets the type of the movie
     * 
     * @return {@link MovieType}
     */
    @Override
    public MovieType getMovieType() {
        return this.type;
    }

    /**
     * Sets the movie type of the movie
     * 
     * @param type is the {@link MovieType} to be set
     */
    public void setMovieType(MovieType type) {
        this.type = type;
    }
}