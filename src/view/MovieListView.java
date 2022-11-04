package view;

import java.util.ArrayList;
import model.Movie;
import model.MovieGoer;
import controller.MovieManager;
import handler.InputHandler;

public class MovieListView extends MainView {
    private ArrayList<Movie> allMovies;
    private MovieGoer movieGoer;

    public MovieListView(MovieGoer movieGoer) {
        this.allMovies = MovieManager.getAllMovieList();
        this.movieGoer = movieGoer;
    }

    public void printMovieList() {
        int size = 0;
        System.out.println("====================================");
        for (int i = 0; i < this.allMovies.size(); i++) {
            size = i + 1;
            Movie movie = allMovies.get(i);
            System.out.println((i + 1) + ". " + movie.getMovieTitle());
        }
        System.out.println((size + 1) + ". Return");

    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a movie to view further details or return to previous page: ");
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        this.printMovieList();
        String movieTitle;

        do {
            this.printMenu();
            choice = InputHandler.intHandler();

            while (choice < 1 || choice > allMovies.size() + 1) {
                System.out.println("Please enter a valid input.");
                choice = InputHandler.intHandler();
            }
            if (choice == (allMovies.size() + 1)) {
                return;
            } else {
                movieTitle = allMovies.get(choice).getMovieTitle();
                MovieDetailsView detailsview = new MovieDetailsView(movieTitle, this.movieGoer);
                detailsview.appContent();
            }
            if (MovieMenuView.exit) {
                return;
            }

        } while (choice > 0 && choice <= allMovies.size());
    }

}