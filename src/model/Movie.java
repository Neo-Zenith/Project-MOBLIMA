package model;

import java.io.Serializable;

public class Movie implements Serializable {
    private String movieTitle;
    private static final long serialVersionUID = 6L;

    public Movie(String movieTitle) {
        this.setMovieTitle(movieTitle);
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
