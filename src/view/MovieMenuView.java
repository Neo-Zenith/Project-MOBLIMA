package view;

import handler.InputHandler;
import handler.UIHandler;
import model.MovieGoer;

public class MovieMenuView {
    public static boolean exit = false;
    private MovieListView movieListView;
    private MovieGoer movieGoer;
    private String errorMessage;

    public MovieMenuView(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
        this.movieListView = new MovieListView(this.movieGoer);
        this.errorMessage = "";
    }

    public void printMenu() {
        MainView.printBoilerPlate("Main Menu");
        MainView.printMenuContent("""

                1. List Movies
                2. Search Movie
                3. View Booking History
                4. List Top 5 Movies
                5. Logout
                """);
    }

    public void appContent() {
        int choice = -1;

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    this.movieListView.appContent();
                    break;
                case 2:
                    MovieSearchView search = new MovieSearchView(this.movieGoer);
                    search.appContent();
                case 3:
                    break;
                case 4:
                    MovieListRankingView rank = new MovieListRankingView();
                    rank.appContent();
                    break;
                case 5:
                    return;
                default:
                    this.errorMessage = "Please enter a valid input!";
            }

            if (MovieMenuView.exit) {
                MovieMenuView.exit = false;
            }

        }   while ((Integer) choice instanceof Integer);
    }
}
