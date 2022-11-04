package view;

import database.Database;
import handler.InputHandler;
import model.Movie;
import model.MovieSchedule;
import controller.MovieManager;

import java.util.ArrayList;


public class MovieTypeView extends MainView {
    private Movie movie;
    private ArrayList <Movie> listOfMovieTypes;
    private int numOfMovieTypes;

    public MovieTypeView(Movie movie) {
        this.movie = movie;
        this.listOfMovieTypes = MovieManager.getMovieList(movie);
        this.numOfMovieTypes = this.listOfMovieTypes.size();
    }

    public void printMovieType() {
        System.out.println("====================================");
        System.out.println("Movie Type Available: ");
        int index = 1;
        for (int i = 0; i < listOfMovieTypes.size(); i ++) {
            Movie movie = listOfMovieTypes.get(i);
            System.out.println(index + ". " + movie.getMovieType());
        }
    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select one of the movie types: ");
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        this.printMovieType();

        do {
            this.printMenu();
            choice = InputHandler.intHandler();

        }   while (choice > 0 && choice < this.numOfMovieTypes);
    }
}
