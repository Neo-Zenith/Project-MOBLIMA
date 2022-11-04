package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.enums.MovieGoerAge;
import handler.DatabaseHandler;

public abstract class MovieGoer implements Serializable {
    private String name;
    private String UUID;
    private String email;
    private String mobileNum;
    private String username;
    private String password;
    private ArrayList <BookingHistory> bookingHistory;
    private List<MovieReview> reviewHistory;
    private final static long serialVersionUID = 9L;
    private static boolean viewTop5OverallRatings;
    private static boolean viewTop5MovieSales;


    public MovieGoer(String UUID, String name, String email, String mobileNum, String username, String password) {
        this.name = name;
        this.UUID = UUID;
        this.email = email;
        this.mobileNum = mobileNum;
        this.username = username;
        this.password = password;
        this.bookingHistory = new ArrayList<>();
        this.reviewHistory = new ArrayList<>();
        this.viewTop5MovieSales = true;
        this.viewTop5OverallRatings = true;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String uUID) {
        UUID = uUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public ArrayList<BookingHistory> getBookingHistory() {
        return this.bookingHistory;
    }

    public void setBookingHistory(ArrayList<BookingHistory> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    public List<MovieReview> getReviewHistory() {
        return reviewHistory;
    }

    public void setReviewHistory(List<MovieReview> reviewHistory) {
        this.reviewHistory = reviewHistory;
    }

    public static boolean getViewTop5OverallRatings() {
        return viewTop5OverallRatings;
    }

    public static void setViewTop5OverallRatings(boolean viewTop5OverallRating) {
        viewTop5OverallRatings = viewTop5OverallRating;
    }

    public static boolean getViewTop5MovieSales() {
        return viewTop5MovieSales;
    }

    public static void setViewTop5MovieSales(boolean viewTop5MovieSale) {
        viewTop5MovieSales = viewTop5MovieSale;
    }

    public abstract MovieGoerAge getMovieGoerAge();

    public abstract double getGoerPrice();



    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}