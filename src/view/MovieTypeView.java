package view;

import database.Database;
import handler.InputHandler;
import model.Movie;
import model.MovieSchedule;
import controller.MovieManager;

import java.util.ArrayList;

public class MovieTypeView extends MainView {
    private String movieTitle;
    private ArrayList<Movie> listOfMovieTypes;
    private int numOfMovieTypes;

    public MovieTypeView(String movieTitle) {
        this.movieTitle = movieTitle;
        this.listOfMovieTypes = MovieManager.getMovieList(movieTitle);
        this.numOfMovieTypes = this.listOfMovieTypes.size();
    }

    public void printMovieType() {
        System.out.println("====================================");
        System.out.println("Movie Type Available: ");
        int index = 1;
        for (int i = 0; i < listOfMovieTypes.size(); i++) {
            Movie movie = listOfMovieTypes.get(i);
            System.out.println(index + ". " + movie.getMovieType());
        }
        System.out.println((this.numOfMovieTypes + 1) + ". Return");
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

            while (choice < 1 || choice > numOfMovieTypes + 1) {
                System.out.println("Please enter a valid input.");
                choice = InputHandler.intHandler();
            }
            if (choice == numOfMovieTypes + 1) {
                return;
            } else {
                CineplexView cineplex = new CineplexView(listOfMovieTypes.get(choice - 1));
                cineplex.appContent();
            }
            if (MovieMenuView.exit) {
                return;
            }
        } while (choice > 0 && choice < this.numOfMovieTypes);
    }
}
