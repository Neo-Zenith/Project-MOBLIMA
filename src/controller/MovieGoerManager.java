package controller;

import java.util.ArrayList;
import java.util.Collections;

import database.Database;
import model.Adult;
import model.Child;
import model.MovieGoer;
import model.MovieReview;
import model.SeniorCitizen;
import model.Movie;
import handler.DatabaseHandler;

public class MovieGoerManager {

    public MovieGoer createMovieGoer(String ageGroup, int userID, String name, String email, String mobileNum,
            int age) {
        String UUID = String.format("MG%03d", DatabaseHandler.generateUUID(Database.CINEMA));
        MovieGoer goer;
        switch (ageGroup) {
            case ("Adult"):
                goer = new Adult(UUID, name, email, mobileNum, age);
                break;
            case ("SeniorCitizen"):
                goer = new SeniorCitizen(UUID, name, email, mobileNum, age);
                break;
            case ("Child"):
                goer = new Child(UUID, name, email, mobileNum, age);
                break;
            default:
                goer = new Adult(UUID, name, email, mobileNum, age);

        }
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }

    public void viewBookingHistory() {
        // for(int i = 0; i<this.bookingHistory.size(); i++){
        // System.out.println( i+1 + ": " + this.bookingHistory.get(i).getMovieTitle());
        // System.out.println("Cinema code: " +
        // this.bookingHistory.get(i).getCinemaCode());
        // System.out.println("Row: " + this.bookingHistory.get(i).getSeatRowID() + "
        // Column: " + this.bookingHistory.get(i).getSeatColumnID());
        // System.out.println("Price: " + this.bookingHistory.get(i).getFinalPrice());
        // if(this.bookingHistory.get(i).getPaymentStatus() == false){
        // System.out.println("Not paid yet!");
        // }
        // else{
        // System.out.println("Ticket paid");
        // }
        // }
    }

    public ArrayList<Movie> rankTop5(String choice) {

        if (Movie.movies.size() <= 1) {
            System.out.println(
                    "1. " + Movie.movies.get(0).getMovieTitle() + " [" + Movie.movies.get(0).getMovieType() + "]");
            return Movie.movies;
        }
        switch (choice) {
            case "ticket":
                for (int j = 1; j < Movie.movies.size(); j++) {
                    for (int k = j; k > 0; k--) {
                        if (Movie.movies.get(k).getMovieTicketsSold() > Movie.movies.get(k - 1).getMovieTicketsSold()) {
                            Collections.swap(Movie.movies, k, k - 1);
                        }
                    }
                }
                break;
            case "ratings":
                for (int j = 1; j < Movie.movies.size(); j++) {
                    for (int k = j; k > 0; k--) {
                        if (Movie.movies.get(k).getMovieOverallReviewRating() > Movie.movies.get(k - 1)
                                .getMovieOverallReviewRating()) {
                            Collections.swap(Movie.movies, k, k - 1);
                        }
                    }
                }
        }
        return Movie.movies;

    }

    public void setUserMovieReview(Movie movie, MovieGoer goer, String movieTitle, String movieType, String review,
            double movieReviewRating) {
        MovieManager manager = new MovieManager();
        for (int i = 0; i < Movie.movies.size(); i++) {
            Movie m;
            m = Movie.movies.get(i);
            if (m.getMovieTitle().equals(movieTitle) && m.getMovieType().equals(movieType)) {
                String UUID = String.format("MR%03d", DatabaseHandler.generateUUID(Database.MOVIE_REVIEW));
                MovieReview newReview = new MovieReview(UUID, goer.getUUID(), movieTitle, movieType, review,
                        movieReviewRating);
                manager.writeMovieReview(movie, newReview);
                goer.getReviewHistory().add(newReview);
                DatabaseManager.saveUpdateToDatabase(UUID, newReview, Database.MOVIE_REVIEW);
                return;
            }
        }
        System.out.println("This movie is unavailable.");
    }

    public MovieGoer getGoerDetails(String name) {
        ArrayList<MovieGoer> goerlist = Database.getValueList(Database.MOVIE_GOER.values());
        for (int i = 0; i < goerlist.size(); i++) {
            if (goerlist.get(i).getName() == name) {
                return goerlist.get(i);
            }
        }
        return null;

    }

}
