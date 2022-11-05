package view;

import java.util.*;
import model.*;
import controller.*;
import handler.*;


public class MovieListView extends MainView {
    private ArrayList<Movie> allMovies;
    private MovieGoer movieGoer;
    private String errorMessage;

    public MovieListView(MovieGoer movieGoer) {
        this.allMovies = MovieManager.getAllMovieList();
        this.movieGoer = movieGoer;
        this.errorMessage = "";
    }

    public void printMovieList() {
        String content = "\n";
        int count = 0;
        for (int i = 0; i < this.allMovies.size(); i++) {
            Movie movie = allMovies.get(i);
            String index = String.format("%02d. ", (i + 1));
            String payload = String.format(index + "%s\n", movie.getMovieTitle());
            content = content + payload;
            count = i + 1;
        }
        String index = String.format("%02d. ", (count + 1));
        String payload = String.format(index + "Return.\n");
        content = content + payload;

        MainView.printMenuContent(content);
    }

    public void printMenu() {
        MainView.printBoilerPlate("Available Movies");
        this.printMovieList();
    }

    public void appContent() {
        int choice = -1;
        String movieTitle;

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();

            if (choice == -1 || choice < 0 || choice > allMovies.size() + 1) {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            if (choice == (allMovies.size() + 1)) {
                this.errorMessage = "";
                return;
            } 
            else {
                movieTitle = allMovies.get(choice - 1).getMovieTitle();
                MovieDetailsView detailsview = new MovieDetailsView(movieTitle, this.movieGoer);
                this.errorMessage = "";
                detailsview.appContent();
            }
            if (MovieMenuView.exit) {
                this.errorMessage = "";
                return;
            }

        }   while (true);
    }

}