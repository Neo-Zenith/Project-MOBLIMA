package view;

import java.util.*;

import controller.MovieReviewManager;
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
        ArrayList<Movie> movies = Database.getValueList(Database.MOVIE.values());
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
        } else {
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
                    4. Review the Movie
                    5. Return
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
            if (choice == 5) {
                this.errorMessage = "";
                return;
            } else {
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
                    case 4:
                        UIHandler.clearScreen();
                        this.errorMessage = "";
                        this.printAddReview();
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
        String dummy = InputHandler.stringHandler();
    }

    public void printPastReviews() {
        MainView.printBoilerPlate("Past Reviews of " + this.movieTitle);

        if (this.movie.getMovieReviews().size() <= 1) {
            MainView.printMenuContent("Reviews are not available yet!");
        } else {
            String content = "\n";

            for (int i = 0; i < pastReviews.size(); i++) {
                String index = String.format("%d. ", i + 1);
                String payload1 = String.format(index + "Rating: " + this.pastReviews.get(i).getMovieReviewRating());
                String payload2 = String.format("\n   Review: %s\n", this.pastReviews.get(i).getReview());
                content = content + payload1 + payload2;
            }
            MainView.printMenuContent(content);
        }

        System.out.println("Press any key to return: ");
        String dummy = InputHandler.stringHandler();
    }

    public void printAddReview() {
        System.out.println("Give a rating for the movie: (0-5)");
        double rating = InputHandler.doubleHandler();
        while (rating < 0 || rating > 5) {
            this.errorMessage = "Error! Please enter a valid input!";
            System.out.println(this.errorMessage);
            rating = InputHandler.doubleHandler();
        }
        Scanner sc  = new Scanner(System.in);
        sc.next();
        System.out.println("Give a review for the movie: ");
        String review = InputHandler.stringHandler();

        MovieReviewManager manager = new MovieReviewManager();
        manager.createMovieReview(this.movieGoer, this.movie, review, rating);
        System.out.println("Review created!");
    }
}