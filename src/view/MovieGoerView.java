package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieGoerManager;
import controller.MovieManager;

import model.MovieGoer;
import model.Movie;
import handler.InputHandler;

public class MovieGoerView extends MainView {
    private MovieScheduleView msview;

    public MovieGoerView() {
        // this.msview = new MovieScheduleView(null)
    }

    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                1. List Movies
                2. Search Movie
                3. View Booking History
                4. List Top 5 Movies
                5. Logout
                """);
        System.out.println("====================================");
    }

    public void printMovieMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                1. Check Seat Availability / Selection of Seats
                2. Return
                """);
        System.out.println("====================================");
    }

    public void printBookingMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                1. Book & Purchase Ticket
                2. Return
                """);
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        do {
            Scanner sc = new Scanner(System.in);
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    // need to display the list of movies and have it print out the cineplex,
                    // cinema, schedules
                    // based on the UUID of the movie that you use the cinema to get the movieOnShow
                    // and the UUID of the movie
                    ArrayList<Movie> movie = MovieManager.getMovieList();
                    for (int i = 0; i < movie.size(); i++) {
                        System.out.println("Movie TItle: " + movie.get(i).getMovieTitle());
                        System.out.println("Showing Status: " + movie.get(i).getMovieShowingStatus());
                        System.out.println("Rating: " + movie.get(i).getMovieOverallReviewRating());
                        System.out.println("Duration: " + movie.get(i).getMovieDuration());
                        System.out.println("Description: " + movie.get(i).getMovieSynopsis());
                    }

                    break;
                case 2:
                    // get the movie UUID and return all the cineplex, schedules that match the UUID
                    // function here

                    this.printMovieMenu();
                    int movieChoice = sc.nextInt();
                    if (movieChoice == 1) {
                        // function here to display seats
                        printBookingMenu();
                        int bookingChoice = sc.nextInt();
                        if (bookingChoice == 1) {
                            // booking function
                        }
                        break;
                    }
                    break;
                case 3:
                    // payment stuff
                    break;
                case 4:
                    this.printTop5();
                    break;

            }
        } while (choice != 5);
    }

    public void printDetails(String name) {

        MovieGoer goer = MovieGoerManager.getGoerDetails(name);
        System.out.println("Name: " + goer.getName());
        System.out.println("Email: " + goer.getEmail());
        System.out.println("Mobile Number: " + goer.getMobileNum());
    }

    public void printTop5() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------");
        System.out.println("Choose 1 or 2");
        System.out.println("1. Rank by ticket sales");
        System.out.println("2. Rank by overall reviewers' ratings");
        System.out.println("--------------------");

        int choice = sc.nextInt();
        if (choice == 1) {
            MovieGoerManager.rankTop5("ticket");
        } else if (choice == 2) {
            MovieGoerManager.rankTop5("ratings");
        }

        if (choice == 1) {
            if (5 > Movie.movies.size()) {
                for (int j = 0; j < Movie.movies.size(); j++) {
                    System.out.println(j + 1 + ". " + Movie.movies.get(j).getMovieTitle() + " ["
                            + Movie.movies.get(j).getMovieType() + "] - Tickets sold: "
                            + Movie.movies.get(j).getMovieTicketsSold());
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    System.out.println(j + 1 + ". " + Movie.movies.get(j).getMovieTitle() + " ["
                            + Movie.movies.get(j).getMovieType() + "] - Tickets sold: "
                            + Movie.movies.get(j).getMovieTicketsSold());
                }
            }
        } else {
            if (5 > Movie.movies.size()) {
                for (int j = 0; j < Movie.movies.size(); j++) {
                    System.out.println(j + 1 + ". " + Movie.movies.get(j).getMovieTitle() + " ["
                            + Movie.movies.get(j).getMovieType() + "] - Overall Rating: "
                            + Movie.movies.get(j).getMovieOverallReviewRating());
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    System.out.println(j + 1 + ". " + Movie.movies.get(j).getMovieTitle() + " ["
                            + Movie.movies.get(j).getMovieType() + "] - Overall Rating: "
                            + Movie.movies.get(j).getMovieOverallReviewRating());
                }
            }
        }
    }

}
