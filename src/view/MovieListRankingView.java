package view;

import java.util.ArrayList;

import controller.MovieGoerManager;
import database.Database;
import handler.InputHandler;
import model.Movie;

public class MovieListRankingView extends MainView {
    public void printMenu(){
        System.out.println("====================================");
        MainView.printBoilerPlate("""
            1. Rank by ticket sales
            2. Rank by overall reviewers' ratings
            3. Return
            Please choose 1 or 2
            """);
        System.out.println("====================================");
    }
    
    public void appContent(){
        boolean result = false; 
        int choice = -1;
        do{
            this.printMenu();
            choice = InputHandler.intHandler();
            while (choice<1 || choice > 3){
                System.out.println("Please enter a valid input");
                choice = InputHandler.intHandler();
            }

            switch(choice){
                case 1:
                    result = MovieGoerManager.rankTop5("ticket", false);
                    break;
                case 2:
                    result = MovieGoerManager.rankTop5("ratings", false);
                    break;
            }

            ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());

            if (choice == 1) {
                if (!result|| 5 > movies.size()) {
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
                if (!result || 5 > movies.size()) {
                    for (int j = 0; j < movies.size(); j++) {
                        System.out.println(j + 1 + ". " + movies.get(j).getMovieTitle() + " ["
                                + movies.get(j).getMovieType() + "] - Overall Rating: "
                                + movies.get(j).getMovieOverallReviewRating());
                    }
                } else {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(j + 1 + ". " + movies.get(j).getMovieTitle() + " ["
                                + movies.get(j).getMovieType() + "] - Overall Rating: "
                                + movies.get(j).getMovieOverallReviewRating());
                    }
                }
            }
        }while(choice != 3);
    }
}
