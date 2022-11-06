package view;
import java.util.ArrayList;

import controller.MovieGoerManager;
import database.Database;
import handler.InputHandler;
import model.Movie;

public class StaffMovieListRankingView extends MainView {
    public void printMenu(){
        MainView.printBoilerPlate("Staff Rank Top 5");
        MainView.printMenuContent("""

            1. Rank by ticket sales
            2. Rank by overall reviewers' ratings
            3. Return
            Please choose 1 or 2
            """);
    }
    
    public void appContent(){
        int choice = -1;
        do{
            this.printMenu();
            choice = InputHandler.intHandler();
            while (choice<1 || choice > 3){
                System.out.println("Please enter a valid input");
                choice = InputHandler.intHandler();
            }

            ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());

            switch(choice){
                case 1:
                    movies = MovieGoerManager.rankTop5("ticket", movies, true);
                    break;
                case 2:
                    movies = MovieGoerManager.rankTop5("ratings", movies, true);
                    break;
            }
            System.out.println("====================================================");
            if (choice == 1) {
                if (5 > movies.size()) {
                    for (int j = 0; j < movies.size(); j++) {
                        System.out.println(j + 1 + ". " + movies.get(j).getMovieTitle() + " ["
                                + movies.get(j).getMovieType() + "] - Tickets sold: "
                                + movies.get(j).getMovieTicketsSold());
                    }
                } else {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(j + 1 + ". " + movies.get(j).getMovieTitle() + " ["
                                + movies.get(j).getMovieType() + "] - Tickets sold: "
                                + movies.get(j).getMovieTicketsSold());
                    }
                }
            } else {
                if (5 > movies.size()) {
                    for (int j = 0; j < movies.size(); j++) {
                        String rating = String.format("%.2f", movies.get(j).getMovieOverallReviewRating());
                        System.out.println(j + 1 + ". " + movies.get(j).getMovieTitle() + " ["
                                + movies.get(j).getMovieType() + "] - Overall Rating: "
                                + rating);
                    }
                } else {
                    for (int j = 0; j < 5; j++) {
                        String rating = String.format("%.2f", movies.get(j).getMovieOverallReviewRating());
                        System.out.println(j + 1 + ". " + movies.get(j).getMovieTitle() + " ["
                                + movies.get(j).getMovieType() + "] - Overall Rating: "
                                + rating);
                    }
                }
            }
        }while(choice != 3);
    }
}
