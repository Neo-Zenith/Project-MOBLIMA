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

    public MovieGoer(String UUID, String name, String email, String mobileNum, String username, String password) {
        this.name = name;
        this.UUID = UUID;
        this.email = email;
        this.mobileNum = mobileNum;
        this.username = username;
        this.password = password;
        this.bookingHistory = new ArrayList<>();
        this.reviewHistory = new ArrayList<>();
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

    public abstract MovieGoerAge getMovieGoerAge();

    public abstract double getGoerPrice();



    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}