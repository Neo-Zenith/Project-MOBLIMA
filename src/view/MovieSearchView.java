package view;

import database.Database;
import handler.InputHandler;
import model.Movie;
import model.MovieGoer;
import model.MovieSchedule;
import controller.MovieManager;

import java.util.ArrayList;

public class MovieSearchView extends MainView {
    private String movieTitle;
    private MovieGoer movieGoer;

    public MovieSearchView(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }

    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("Enter Movie Title you want to search: (Press 1 to exit)");
        System.out.println("====================================");
    }

    public void appContent() {
        String movieTitle;
        boolean available = false;

        do {
            this.printMenu();
            movieTitle = InputHandler.stringHandler();

            if (movieTitle.equals("1")) {
                return;
            }
            ArrayList<Movie> movies = Database.getValueList(Database.MOVIE.values());
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getMovieTitle().equals(movieTitle)) {
                    this.movieTitle = movieTitle;
                    available = true;
                    break;
                }
            }
            if (available) {
                MovieDetailsView detailsView = new MovieDetailsView(movieTitle, movieGoer);
                detailsView.appContent();
            } else {
                System.out.println("This movie is not available.");
            }

            if (MovieMenuView.exit) {
                return;
            }
        } while (!available);
    }
}