package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieGoerManager;
import controller.MovieManager;
import database.Database;
import model.MovieGoer;
import model.MovieSchedule;
import model.enums.CinemaClass;
import model.enums.MovieType;
import model.Cinema;
import model.Cineplex;
import model.DateTime;
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
                    ArrayList<MovieSchedule> movieList1 = Database.getValueList(Database.MOVIE_SCHEDULE.keySet());
                    ArrayList<String> existingMovie = new ArrayList<String>();
                    System.out.println("Choose one of the movie to view more details:");
                    for (int i = 0; i < movieList1.size(); i++) {
                        for (int j = 0; j < existingMovie.size(); j++) {
                            if (!existingMovie.get(j).equals(movieList1.get(i).getMovieOnShow().getMovieTitle())) {
                                existingMovie.add(movieList1.get(i).getMovieOnShow().getMovieTitle());
                            }
                        }
                    }
                    for (int k = 0; k < existingMovie.size(); k++) {
                        System.out.println((k + 1) + ". " + existingMovie.get(k));
                    }

                    int choice1 = sc.nextInt();
                    String movieTitle1 = new String();
                    int breaker = 0;
                    while (true) {
                        if (breaker == 1) {
                            break;
                        }
                        for (int j = 0; j < movieList1.size(); j++) {
                            if ((j + 1) == choice1) {
                                breaker = 1;
                                movieTitle1 = movieList1.get(j).getMovieOnShow().getMovieTitle();
                                break;
                            }
                        }
                        System.out.println("Please enter a valid choice.");
                    }
                    MovieSchedule schedule1 = this.printMovieType(movieTitle1);
                    this.printMovieDetails(movieTitle1);
                    Cineplex cineplex1 = this.printCineplexes(schedule1);
                    Cinema cinema1 = this.printCinemas(cineplex1, schedule1);
                    DateTime time1 = this.printShowingTime(cinema1, schedule1);

                    int movieChoice1 = sc.nextInt();
                    if (movieChoice1 == 1) {
                        // function here to display seats
                        printBookingMenu();
                        int bookingChoice1 = sc.nextInt();
                        if (bookingChoice1 == 1) {
                            // booking function
                        }
                        break;
                    }

                    break;
                case 2:
                    // get the movie UUID and return all the cineplex, schedules that match the UUID
                    // function here
                    System.out.println("What movie title are you searching for? ");
                    String movieTitle2 = sc.nextLine();
                    MovieSchedule schedule2 = this.printMovieType(movieTitle2);
                    this.printMovieDetails(movieTitle2);
                    Cineplex cineplex2 = this.printCineplexes(schedule2);
                    Cinema cinema2 = this.printCinemas(cineplex2, schedule2);
                    DateTime time2 = this.printShowingTime(cinema2, schedule2);

                    int movieChoice2 = sc.nextInt();
                    if (movieChoice2 == 1) {
                        // function here to display seats
                        printBookingMenu();
                        int bookingChoice2 = sc.nextInt();
                        if (bookingChoice2 == 1) {
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
            MovieGoerManager.rankTop5("ticket",  false);
        } else if (choice == 2) {
            MovieGoerManager.rankTop5("ratings", false);
        }

        
}
