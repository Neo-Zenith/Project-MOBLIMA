package view;

import handler.InputHandler;
import model.MovieGoer;

public class MovieMenuView {
    public static boolean exit = false;
    private MovieListView movieListView;
    private MovieGoer movieGoer;

    public MovieMenuView(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
        this.movieListView = new MovieListView(this.movieGoer);
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

    public void appContent() {
        int choice = -1;

        do {
            this.printMenu();
            choice = InputHandler.intHandler();

            while (choice < 1 || choice > 5) {
                System.out.println("Please enter a valid input.");
                choice = InputHandler.intHandler();
            }

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
                    break;

            }
        } while (choice > 0 && choice < 5);
    }
}
