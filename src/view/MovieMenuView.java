package view;

import handler.InputHandler;

public class MovieMenuView {
    private MovieListView movieListView;

    public MovieMenuView() {
        this.movieListView = new MovieListView();
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

            switch (choice) {
                case 1:
                    this.movieListView.appContent();
                    break;
            }
        }   while (choice > 0 && choice < 5);
    }
}
