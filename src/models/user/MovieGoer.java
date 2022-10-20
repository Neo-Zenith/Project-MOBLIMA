package models.user;

import models.movie.*;
import models.cinema.*;

import java.time.LocalDateTime;
import java.util.*;

public class MovieGoer extends HumanUser {
    private String name;
    private int userID;
    private String email;
    private String mobileNum;
    private List <Payment> bookingHistory;
    private AgeGroup age;
    private List <MovieReview> reviewHistory;

    public MovieGoer(int userID, String name, String email, String mobileNum, AgeGroup age) {
        super(name, userID);
        this.email = email;
        this.mobileNum = mobileNum;
        this.age = age;
        this.bookingHistory = new ArrayList<>();
        this.reviewHistory = new ArrayList<>();
    } 

    public String getEmail() {
        return this.email;
    }

    public String getMobileNum() {
        return this.mobileNum;
    }

    public AgeGroup getAge() {
        return this.age;
    }

    public List <MovieReview> getUserMovieReview() {
        return this.reviewHistory;
    }

    public List <Payment> getBookingHistory() {
        return this.bookingHistory;
    }

    public void setUserMovieReview() {
        for (int i = 0; i < Movie.movies.size(); i ++) {
            List <MovieReview> movieReviews = Movie.movies.get(i).getListOfMovieReviews();
            for (int j = 0; j < movieReviews.size(); j ++) {
                if (movieReviews.get(j).getMovieGoerID() == this.userID) {
                    this.reviewHistory.add(movieReviews.get(j));
                }
            }
        }
    }

    public void bookMovieTicket(Cinema showingCinema, Movie movie, DateTime showingTime, 
                                Seat seat, float discount, boolean byPercentage,
                                boolean isHoliday) {

        MovieTicket movieTicket = new MovieTicket(seat, showingCinema, movie, showingTime);
        movieTicket.calculateTicketPrice(discount, byPercentage, isHoliday);
        
        String cinemaCode = String.format("%03d", showingCinema.getCinemaID());
        String year = String.format("04d", Calendar.getInstance().get(Calendar.YEAR));
        String month = String.format("02d", Calendar.getInstance().get(Calendar.MONTH));
        String date = String.format("02d", Calendar.getInstance().get(Calendar.DATE));
        String hour = String.format("02d", Calendar.getInstance().get(Calendar.HOUR));
        String minute = String.format("02d", Calendar.getInstance().get(Calendar.MINUTE));

        String transactionID = cinemaCode + year + month + date + hour + minute;
        Payment payment = new Payment(  transactionID, movie.getMovieTitle(), 
                                        seat.getRowID(), seat.getColumnID());
        bookingHistory.add(payment);
    }
}
