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
import model.BookingHistory;
import model.Student;
import handler.DatabaseHandler;
import model.enums.MovieGoerAge;

public class MovieGoerManager {

    public static MovieGoer createGoerAdult(MovieGoerAge age, String name, String email, String mobileNum, String username, String password){
        String UUID = String.format("MG%03d", DatabaseHandler.generateUUID(Database.MOVIE_GOER));
        MovieGoer goer = new Adult(UUID, name, email, mobileNum, username, password);
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }

    public static MovieGoer createGoerChild(MovieGoerAge age, String name, String email, String mobileNum, String username, String password){
        String UUID = String.format("MG%03d", DatabaseHandler.generateUUID(Database.MOVIE_GOER));
        MovieGoer goer = new Child(UUID, name, email, mobileNum, username, password);
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }

    public static MovieGoer createGoerSeniorCitizen(MovieGoerAge age, String name, String email, String mobileNum, String username, String password){
        String UUID = String.format("MG%03d", DatabaseHandler.generateUUID(Database.MOVIE_GOER));
        MovieGoer goer = new SeniorCitizen(UUID, name, email, mobileNum, username, password);
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }
    
    public static MovieGoer createGoerStudent(MovieGoerAge age, String name, String email, String mobileNum, String username, String password){
        String UUID = String.format("MG%03d", DatabaseHandler.generateUUID(Database.MOVIE_GOER));
        MovieGoer goer = new Student(UUID, name, email, mobileNum, usernamem, password);
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }

    public static void viewBookingHistory(MovieGoer movieGoer) {
        for(int i = 0; i < movieGoer.getBookingHistory().size(); i++) {
            BookingHistory bookingHistory = movieGoer.getBookingHistory().get(i);
            System.out.println( i+1 + ": " + bookingHistory.getMovieTicket().getMovieToWatch());
            System.out.println("Transaction ID: " +
            bookingHistory.getPayment().getTransactionID());
            System.out.println("Row: " + this.bookingHistory.get(i).getSeatRowID() + 
            "Column: " + this.bookingHistory.get(i).getSeatColumnID());
            System.out.println("Price: " + this.bookingHistory.get(i).getFinalPrice());
            if (this.bookingHistory.get(i).getPaymentStatus() == false){
            	System.out.println("Not paid yet!");
            }
            else{
            	System.out.println("Ticket paid");
            }
    }

    public static void rankTop5(String choice, boolean staff) {

        if(!staff){
            if(!MovieGoer.getViewTop5MovieSales()){
                System.out.println("Ranking by Top 5 Movie Sales is unavailable");
                return; 
            }

            if(!MovieGoer.getViewTop5OverallRatings()){
                System.out.println("Ranking by Top 5 Overall Sales is unavailable");
                return;
            }
        }
        if (Movie.movies.size() <= 1) {
            return;
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
        return;

    }

    public static void setUserMovieReview(Movie movie, MovieGoer goer, String movieTitle, String movieType,
            String review,
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

    public static MovieGoer getGoerDetails(String name) {
        ArrayList<MovieGoer> goerlist = Database.getValueList(Database.MOVIE_GOER.values());
        for (int i = 0; i < goerlist.size(); i++) {
            if (goerlist.get(i).getName() == name) {
                return goerlist.get(i);
            }
        }
        return null;

    }


}
