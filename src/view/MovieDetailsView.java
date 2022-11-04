package view;

import java.util.ArrayList;
import model.Movie;
import model.MovieReview;
import controller.MovieManager;
import handler.InputHandler;

public class MovieDetailsView extends MainView {
    private String movieTitle;
    private Movie movie;
    private String synopsis;
    private ArrayList<MovieReview> pastReviews;

    public MovieDetailsView(String title) {
        this.movieTitle = title;
        for (int i = 0; i < Movie.movies.size(); i++) {
            if (movieTitle.equals(Movie.movies.get(i).getMovieTitle())) {
                movie = Movie.movies.get(i);
                break;
            }
        }
        synopsis = movie.getMovieSynopsis();
        pastReviews = movie.getMovieReviews();
    }

    public void printMovieDetails() {
        int size = 0;
        System.out.println("====================================");

        System.out.println("Movie Title: " + movie.getMovieTitle());
        System.out.println("Showing Status: " + movie.getMovieShowingStatus());
        System.out.println("Movie Director: " + movie.getMovieDirector());
        System.out.println("Overall Rating: " + movie.getMovieOverallReviewRating());
        System.out.println("Movie Cast: ");
        for (int j = 0; j < movie.getMovieCast().size(); j++) {
            System.out.println(movie.getMovieCast().get(j));
        }
    }

    @Override
    public void printMenu() {
        MainView.printBoilerPlate("""
                    1. View Synopsis
                    2. View Past Reviews
                    3. Choose Movie Type
                    4. Return
                """);
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        this.printMovieDetails();

        do {
            this.printMenu();
            choice = InputHandler.intHandler();

            while (choice < 1 || choice > 4) {
                System.out.println("Please enter a valid input.");
                choice = InputHandler.intHandler();
            }
            if (choice == 4) {
                return;
            } else {
                switch (choice) {
                    case 1:
                        this.printSynopsis();
                        break;
                    case 2:
                        this.printPastReviews();
                        break;
                    case 3:
                        MovieTypeView typeView = new MovieTypeView(movie.getMovieTitle());
                        typeView.appContent();
                        break;
                }
            }
            if (MovieMenuView.exit) {
                return;
            }
        } while (choice > 0 && choice <= 4);
    }

    public void printSynopsis() {
        System.out.println("Synopsis: ");
        System.out.println(this.synopsis);
    }

    public void printPastReviews() {
        System.out.println("Past Reviews: ");
        for (int i = 0; i < pastReviews.size(); i++) {
            System.out.println(this.pastReviews.get(i));
        }
    }
}