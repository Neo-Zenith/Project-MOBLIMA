package view;

import java.util.*;

import controller.*;
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
    private ArrayList <Movie> listOfMovieTypes;

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
        this.listOfMovieTypes = MovieManager.getMovieList(movieTitle);
    }

    public void printMovieDetails() {
        System.out.println("Movie Director: " + movie.getMovieDirector());
        if (movie.getMovieReviews().size() < 2) {
            System.out.println("Overall Rating: Not Available!");
        } else {
            String rating = String.format("%.2f", movie.getMovieOverallReviewRating());
            System.out.println("Overall Rating: " + rating);
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

                Select an option to view further information about the movie: 
                
                01. View Synopsis
                02. View Past Reviews
                03. Booking Query
                04. Review the Movie
                05. Quit and return back
                """);
    }

    public void appContent() {
        int choice = -1;

        do {
            if (MovieMenuView.exit) {
                this.errorMessage = "";
                return;
            }

            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();
            if (choice < 0 || choice > 5) {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
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
                case 5:
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

        if (this.movie.getMovieReviews().size() <= 0) {
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
        InputHandler.stringHandler();
    }

    public void printAddReview() {
        String errorMessage = "";
        Movie movie;

        do {
            MainView.printBoilerPlate("Adding Reviews for " + this.movieTitle);
            this.printMovieType();
            int choice = InputHandler.intHandler();

            if (choice < 1 || choice > this.listOfMovieTypes.size() + 1) {
                errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            if (choice == this.listOfMovieTypes.size() + 1) {
                return;
            }
            movie = this.listOfMovieTypes.get(choice - 1);
            break;
        }   while(true);
        
        System.out.println("Give a review for the movie: ");
        String review = InputHandler.stringHandler();
        System.out.println("Give a rating for the movie: (0-5)");
        double rating = InputHandler.doubleHandler();
        while (rating < 0 || rating > 5) {
            this.errorMessage = "Error! Please enter a valid input!";
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            MainView.printBoilerPlate("Adding Reviews for " + this.movieTitle);
            System.out.println("Give a rating for the movie: (0-5)");
            rating = InputHandler.doubleHandler();
        }
        System.out.println("Review Created!!");
        MovieReviewManager manager = new MovieReviewManager();
        manager.createMovieReview(this.movieGoer, movie, review, rating);
    }


    public void printMovieType() {
        String content = "\nSelect the movie type: \n\n";

        int count = 0;
        for (int i = 0; i < listOfMovieTypes.size(); i++) {
            Movie movie = listOfMovieTypes.get(i);
            String index = String.format("%d. ", i + 1);
            String payload = String.format(index + "%s\n", movie.getMovieType().getDisplayName());
            content = content + payload;
            count = i + 1;
        }
        String index = String.format("%d. ", count + 1);
        String payload = String.format(index + "Quit and return back");
        content = content + payload;

        MainView.printMenuContent(content);
    }
}