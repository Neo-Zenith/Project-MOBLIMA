package view;

import java.util.ArrayList;

import controller.MovieGoerManager;
import database.Database;
import handler.InputHandler;
import handler.UIHandler;
import model.Movie;

public class MovieListRankingView extends MainView {
    private String errorMessage;

    public MovieListRankingView() {
        this.errorMessage = "";
    }

    public void printMenu() {
        MainView.printBoilerPlate("Rank Top 5");
        MainView.printMenuContent("""

                01. Rank by ticket sales
                02. Rank by overall reviewers' ratings
                03. Return
                """);
    }

    public void printTop5(int choice) {
        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());
        String content = "\n";
        String payload;
        String index;

        switch (choice) {
            case 1:
                MainView.printBoilerPlate("Ranking by ticket sales");
                movies = MovieGoerManager.rankTop5("ticket", movies, false);
                if (movies == null) {
                    content += "Ranking by Top 5 Movie Sales is unavailable";
                    MainView.printMenuContent(content);
                    System.out.println("Press any key to return");
                    InputHandler.stringHandler();
                    return;
                }
                break;
            case 2:
                MainView.printBoilerPlate("Ranking by overall rating");
                movies = MovieGoerManager.rankTop5("ratings", movies, false);
                if (movies == null) {
                    content += "Ranking by Top 5 Movie Overall Review Ratings is unavailable";
                    MainView.printMenuContent(content);
                    System.out.println("Press any key to return");
                    InputHandler.stringHandler();
                    return;
                }
                break;
        }
    
        int length = (movies.size() > 5) ? 5 : movies.size();

        if (choice == 1) {
            for (int j = 0; j < length; j++) {
                index = String.format("%d. ", j + 1);
                payload = String.format(index + movies.get(j).getMovieTitle() + " [" +
                                        movies.get(j).getMovieType() + "] - Tickets sold: "
                                        + movies.get(j).getMovieTicketsSold() + "\n");
                content = content + payload;
            }

        } 
        else {
            for (int j = 0; j < length; j++) {
                String rating = String.format("%.1f", movies.get(j).getMovieOverallReviewRating());
                index = String.format("%d. ", j + 1);
                payload = String.format(index + movies.get(j).getMovieTitle() + " [" +
                                        movies.get(j).getMovieType() + "] - Overall rating: "
                                        + rating + "\n");
                content = content + payload;
            }
        }
        MainView.printMenuContent(content);
        System.out.println("Press any key to return");
        InputHandler.stringHandler();
    }

    public void appContent() {
        int choice = -1;
        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();
            if (choice == 3) {
                this.errorMessage = "";
                return;
            }
            if (choice < 1 || choice > 3) {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            UIHandler.clearScreen();
            this.printTop5(choice);
            
        } while (true);
    }
}
