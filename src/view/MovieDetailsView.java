package view;

import java.util.*;
import model.*;
import handler.*;
import database.*;


public class MovieDetailsView extends MainView {
    private String movieTitle;
    private Movie movie;
    private String synopsis;
    private ArrayList<MovieReview> pastReviews;
    private MovieGoer movieGoer;
    private String errorMessage;

    public MovieDetailsView(String title, MovieGoer movieGoer) {
        this.movieTitle = title;
        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());
        for (int i = 0; i < movies.size(); i++) {
            if (movieTitle.equals(movies.get(i).getMovieTitle())) {
                movie = movies.get(i);
                break;
            }
        }
        this.synopsis = movie.getMovieSynopsis();
        this.pastReviews = movie.getMovieReviews();
        this.movieGoer = movieGoer;
        this.errorMessage = "";
    }

    public void printMovieDetails() {
        System.out.println("Showing Status: " + movie.getMovieShowingStatus().getDisplayName());
        System.out.println("Movie Director: " + movie.getMovieDirector());
        if (movie.getMovieReviews().size() == 0) {
            System.out.println("Overall Rating: Not Available!");
        }
        else {
            System.out.println("Overall Rating: " + movie.getMovieOverallReviewRating());
        }
        System.out.print("Movie Cast: ");
        for (int j = 0; j < movie.getMovieCast().size(); j++) {
            System.out.print(movie.getMovieCast().get(j));
            System.out.print(", ");
        }
        System.out.println("...");
    }

    public void printMenu() {
        MainView.printBoilerPlate(this.movieTitle);
        this.printMovieDetails();
        MainView.printMenuContent("""

                    1. View Synopsis
                    2. View Past Reviews
                    3. Choose Movie Type
                    4. Return
                """);
    }

    public void appContent() {
        int choice = -1;

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();

            if (choice < 0 || choice > 4) {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            if (choice == 4) {
                this.errorMessage = "";
                return;
            } 
            else {
                switch (choice) {
                    case 1:
                        UIHandler.clearScreen();
                        this.errorMessage = "";
                        this.printSynopsis();
                        break;
                    case 2:
                        UIHandler.clearScreen();
                        this.errorMessage = "";
                        this.printPastReviews();
                        break;
                    case 3:
                        MovieTypeView typeView = new MovieTypeView(movie.getMovieTitle(), this.movieGoer);
                        this.errorMessage = "";
                        typeView.appContent();
                        break;
                }
            }
            if (MovieMenuView.exit) {
                this.errorMessage = "";
                return;
            }
        } while (true);
    }

    public void printSynopsis() {
        MainView.printBoilerPlate("Synopsis of " + this.movieTitle);
        MainView.printMenuContent(this.synopsis);
        System.out.println("Press any key to return: ");
        InputHandler.stringHandler();
    }

    public void printPastReviews() {
        MainView.printBoilerPlate("Past Reviews of " + this.movieTitle);

        if (this.movie.getMovieReviews().size() <= 1) {
            MainView.printMenuContent("Reviews are not available yet!");
        }
        else {
            String content = "\n";

            for (int i = 0; i < pastReviews.size(); i++) {
                String index = String.format("%d. ", i + 1);
                String payload = String.format(index + "%s\n", this.pastReviews.get(i));
                content = content + payload;
            }
            MainView.printMenuContent(content);
        }

        System.out.println("Press any key to return: ");
        InputHandler.stringHandler();
    }
}