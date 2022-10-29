package models.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class MovieGoer {
    private String name;
    private int userID;
    private String email;
    private String mobileNum;
    // private List <Payment> bookingHistory;
    private List<MovieReview> reviewHistory;

    public MovieGoer(int userID, String name, String email, String mobileNum) {
        this.name = name;
        this.email = email;
        this.mobileNum = mobileNum;
        // this.bookingHistory = new ArrayList<>();
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

        System.out.println("Here is your Movie Ticket Details:");
        System.out.println("Name: " + this.name);
        System.out.println("Movie Title: " + m.getMovieTitle());
        System.out.println("Movie Type: " + movieType);
        System.out.print("Showing Time: ");
        showingTime.printTime();

    }

    public void setUserMovieReview(String movieTitle, String review, float movieReviewRating) {
        MovieReview newReview = new MovieReview(this.userID, movieTitle, review, movieReviewRating);

        for (int i = 0; i < Movie.movies.size(); i++) {
            Movie m;
            m = Movie.movies.get(i);
            if (m.getMovieTitle().equals(movieTitle)) {
                m.writeMovieReview(newReview);
                this.reviewHistory.add(newReview);
                return;
            }
        }
        System.out.println("This movie is unavailable.");
    }

    public abstract double getPrice();
}