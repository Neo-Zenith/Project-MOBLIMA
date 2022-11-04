package view;

import java.util.ArrayList;
import model.Movie;
import controller.MovieManager;

public class MovieListView extends MainView {
    private ArrayList <Movie> allMovies;

    public MovieListView() {
        this.allMovies = MovieManager.getAllMovieList();
    }

    public void printMovieList() {
        System.out.println("====================================");
        for (int i = 0; i < this.allMovies.size(); i ++) {
            Movie movie = allMovies.get(i);
            System.out.println((i + 1) + ". " + movie.getMovieTitle());
        }
    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a movie to view further details: ");
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        this.printMovieList();

        do {
            this.printMenu();
            
        }   while (choice > 0 && choice <= allMovies.size());
    }
}
