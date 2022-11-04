package view;

import model.MovieSchedule;
import controller.MovieScheduleManager;
import model.Cinema;
import model.Movie;

import java.util.ArrayList;

public class MovieScheduleView {
    private Movie movie;
    private Cinema cinema;
    private MovieSchedule movieSchedule;

    public MovieScheduleView(Cinema cinema, Movie movie) {
        this.movie = movie;
        this.cinema = cinema;
        this.movieSchedule = MovieScheduleManager.filterMovieSchedulesByMovie(movie);
    }
    
    public void printMovieSchedule() {
        ArrayList <Cinema> showingVenues = this.movieSchedule.getShowingVenues();
        for (int i = 0; i < showingVenues.size(); i ++) {
            Cinema showingVenue = showingVenues.get(i);

            if (showingVenue.getUUID().equals(this.cinema.getUUID())) {

            }
        }

    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a cinema to view the movie schedule: ");
        System.out.println("====================================");
    }

    public void appContent() {
        this.printMenu();
    }
}
