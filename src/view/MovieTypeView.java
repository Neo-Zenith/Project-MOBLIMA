package view;

import handler.*;
import model.*;
import controller.*;

import java.util.*;

public class MovieTypeView extends MainView {
    private String movieTitle;
    private ArrayList<Movie> listOfMovieTypes;
    private MovieGoer movieGoer;
    private String errorMessage;

    public MovieTypeView(String movieTitle, MovieGoer movieGoer) {
        this.movieTitle = movieTitle;
        this.listOfMovieTypes = MovieManager.getMovieList(movieTitle);
        this.movieGoer = movieGoer;
        this.errorMessage = "";
    }

    public void printMovieType() {
        String content = "\n";

        int count = 0;
        for (int i = 0; i < listOfMovieTypes.size(); i++) {
            Movie movie = listOfMovieTypes.get(i);
            String index = String.format("%d. ", i + 1);
            String payload = String.format(index + "%s\n", movie.getMovieType().getDisplayName());
            content = content + payload;
            count = i + 1;
        }
        String index = String.format("%d. ", count + 1);
        String payload = String.format(index + "Return.");
        content = content + payload;

        MainView.printMenuContent(content);
    }

    public void printMenu() {
        MainView.printBoilerPlate("Movie Type for " + this.movieTitle);
        this.printMovieType();
    }

    public void appContent() {
        int choice = -1;

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();

            if (choice < 0 || choice > this.listOfMovieTypes.size() + 1) {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            if (choice == this.listOfMovieTypes.size() + 1) {
                this.errorMessage = "";
                return;
            } 
            else {
                this.errorMessage = "";
                CineplexView cineplex = new CineplexView(listOfMovieTypes.get(choice - 1), this.movieGoer);
                cineplex.appContent();
            }
            if (MovieMenuView.exit) {
                this.errorMessage = "";
                return;
            }
        } while (true);
    }
}
