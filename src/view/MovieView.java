package view;

import java.util.Scanner;
import controller.MovieGoerManager;
import model.Movie;

public class MovieView {

    public void printTop5() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Choose 1 or 2");
        System.out.println("1. Rank by ticket sales");
        System.out.println("2. Rank by overall reviewers' ratings");
        System.out.println("--------------");
        MovieGoerManager manager = new MovieGoerManager();

        int choice = sc.nextInt();
        if (choice == 1) {
            manager.rankTop5("ticket");
        } else if (choice == 2) {
            manager.rankTop5("ratings");
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
