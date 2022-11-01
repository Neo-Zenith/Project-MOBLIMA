package models.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import models.user.*;

public abstract class MovieGoer {
    private String name;
    private int userID;
    private String email;
    private String mobileNum;
    private List <Payment> bookingHistory;
    private List<MovieReview> reviewHistory;

    public MovieGoer(int userID, String name, String email, String mobileNum) {
        this.name = name;
        this.email = email;
        this.mobileNum = mobileNum;
        this.bookingHistory = new ArrayList<>();
        this.reviewHistory = new ArrayList<>();
    }

    public String getEmail() {
        return this.email;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public List<MovieReview> getUserMovieReview() {
        return this.reviewHistory;
    }

    public void getDetails() {
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
        System.out.println("Mobile Number: " + this.mobileNum);
    }

    public void buyTicket() {
        // *****Still need to add in Cinema Detail and seat details******
        Scanner sc = new Scanner(System.in);

        Movie m;
        ArrayList<String> movieList = new ArrayList<String>();
        for (int i = 0; i < Movie.movies.size(); i++) {
            m = Movie.movies.get(i);
            if (m.getShowingStatus() == MovieShowingStatus.NOW_SHOWING) {
                if (!movieList.contains(m.getMovieTitle())) {
                    movieList.add(m.getMovieTitle());
                }
            }
        }

        System.out.println("What Movie you want to watch: ");
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println(movieList.get(i));
        }
        String movieTitle = sc.next();
        System.out.println(Movie.movies.size());
        ArrayList<String> movieTypeList = new ArrayList<String>();
        for (int k = 0; k < Movie.movies.size(); k++) {
            m = Movie.movies.get(k);
            if (m.getMovieTitle().equals(movieTitle)) {
                if (!movieTypeList.contains(m.getMovieType())) {
                    movieTypeList.add(m.getMovieType());
                }
            }
        }
        System.out.println(movieTypeList.size());
        System.out.println("Movie Type Available for this Movie: ");
        for (int i = 0; i < movieTypeList.size(); i++) {
            System.out.println(movieTypeList.get(i));
        }
        String movieType = sc.next();

        int num = 0;
        for (int j = 0; j < Movie.movies.size(); j++) {
            m = Movie.movies.get(j);
            if (m.getMovieTitle() == movieTitle) {
                num = j;
                break;
            }
        }

        System.out.println("Choose the ShowingTime you want: ");
        m = Movie.movies.get(num);
        m.printShowingTime();
        int time = sc.nextInt();
        DateTime showingTime = m.getShowingTime().get(time);
        m.movieTicketSold();

        System.out.println("Here is your Movie Ticket Details:");
        System.out.println("Name: " + this.name);
        System.out.println("Movie Title: " + m.getMovieTitle());
        System.out.println("Movie Type: " + movieType);
        System.out.print("Showing Time: ");
        showingTime.printTime();

    }

    public void setUserMovieReview(String movieTitle, String movieType, String review, double movieReviewRating) {
        MovieReview newReview = new MovieReview(this.userID, movieTitle, movieType, review, movieReviewRating);

        for (int i = 0; i < Movie.movies.size(); i++) {
            Movie m;
            m = Movie.movies.get(i);
            if (m.getMovieTitle().equals(movieTitle) && m.getMovieType().equals(movieType)){
                m.writeMovieReview(newReview);
                this.reviewHistory.add(newReview);
                return;
            }
        }
        System.out.println("This movie is unavailable.");
    }

    public abstract double getPrice();

    public void viewBookingHistory(){
        // for(int i = 0; i<this.bookingHistory.size(); i++){
        //     System.out.println( i+1 + ": " + this.bookingHistory.get(i).getMovieTitle());
        //     System.out.println("Cinema code: " + this.bookingHistory.get(i).getCinemaCode());
        //     System.out.println("Row: " + this.bookingHistory.get(i).getSeatRowID() + " Column: " + this.bookingHistory.get(i).getSeatColumnID());
        //     System.out.println("Price: " + this.bookingHistory.get(i).getFinalPrice());
        //     if(this.bookingHistory.get(i).getPaymentStatus() == false){
        //         System.out.println("Not paid yet!");
        //     }
        //     else{
        //         System.out.println("Ticket paid");
        //     }
        // }
    }

    public void rankTop5(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------");
        System.out.println("Choose 1 or 2");
        System.out.println("1. Rank by ticket sales");
        System.out.println("2. Rank by overall reviewers' ratings");
        System.out.println("--------------");
        int choice = sc.nextInt();
        if(Movie.movies.size()<=1){
            System.out.println("1. " + Movie.movies.get(0).getMovieTitle() + " [" +  Movie.movies.get(0).getMovieType() + "]");
            return;
        }
        switch(choice){
            case 1:
                for(int j=1; j<Movie.movies.size(); j++){
                    for(int k = j; k>0; k--){
                        if(Movie.movies.get(k).getMovieTicketsSold()>Movie.movies.get(k-1).getMovieTicketsSold()){
                            Collections.swap(Movie.movies, k, k-1);
                        }
                    }
                } 
                break;
            case 2:
                for(int j=1; j<Movie.movies.size(); j++){
                    for(int k = j; k>0; k--){
                        if(Movie.movies.get(k).getMovieOverallReviewRating()>Movie.movies.get(k-1).getMovieOverallReviewRating()){
                            Collections.swap(Movie.movies, k, k-1);
                        }
                    }
                }      
        }
        if(choice == 1){
            if(5 > Movie.movies.size()){
                for(int j = 0; j<Movie.movies.size(); j++){
                    System.out.println(j+1 + ". " + Movie.movies.get(j).getMovieTitle() + " [" +  Movie.movies.get(j).getMovieType() + "] - Tickets sold: " + Movie.movies.get(j).getMovieTicketsSold());
                }
            }
            else{
                for(int j = 0; j<5; j++){
                    System.out.println(j+1 + ". " + Movie.movies.get(j).getMovieTitle() + " [" +  Movie.movies.get(j).getMovieType() + "] - Tickets sold: " + Movie.movies.get(j).getMovieTicketsSold());
                }
            }      
        }
        else{
            if(5 > Movie.movies.size()){
                for(int j = 0; j<Movie.movies.size(); j++){
                    System.out.println(j+1 + ". " + Movie.movies.get(j).getMovieTitle() + " [" +  Movie.movies.get(j).getMovieType() + "] - Overall Rating: " + Movie.movies.get(j).getMovieOverallReviewRating());
                }
            }
            else{
                for(int j = 0; j<5; j++){
                    System.out.println(j+1 + ". " + Movie.movies.get(j).getMovieTitle() + " [" +  Movie.movies.get(j).getMovieType() + "] - Overall Rating: " + Movie.movies.get(j).getMovieOverallReviewRating()   );
                }
            }
        }    
    }
    public void viewMovieDetails(){
        System.out.println("What movie do you want to search for? :");
        Scanner sc = new Scanner(System.in);
        String movieTitle = sc.next();
        ArrayList<String> movieTypeList = new ArrayList<String>();
        for (int k = 0; k < Movie.movies.size(); k++) {
            if((Movie.movies.get(k).getMovieTitle()).equals(movieTitle)){
                System.out.println("Movie Title: " + Movie.movies.get(k).getMovieTitle() + " (" + Movie.movies.get(k).getShowingStatus() + ")");
                System.out.println("Movie Type: " + Movie.movies.get(k).getMovieType());
                System.out.println("Rated: " + Movie.movies.get(k).getMovieAgeRating());
                System.out.println("Duration: " + Movie.movies.get(k).getMovieDuration());
                System.out.println("Review rating: " + Movie.movies.get(k).getMovieOverallReviewRating());
                System.out.println("Director: " + Movie.movies.get(k).getMovieDirector());
                System.out.print("Cast: ");
                for (int j = 0; j < Movie.movies.get(k).getMovieCast().size(); j++) {
                    System.out.print(Movie.movies.get(k).getMovieCast().get(j) + " ");
                }
                System.out.println("");
                System.out.println("Synopsis: " + Movie.movies.get(k).getMovieSynopsis());
                // System.out.print("Showing venues: ");
                // for(int j=0; j<m.showingVenue.size(); j++){
                // System.out.print(m.showingVenue.get(j).getCinemaID() + " ");
                // }
                System.out.println("Price: $" + Movie.movies.get(k).getMoviePrice());
                Movie.movies.get(k).printShowingTime();
            }
        }

    }
}