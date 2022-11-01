package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import handler.DatabaseHandler;

public abstract class MovieGoer implements Serializable {
    private String name;
    private String UUID;
    private String email;
    private String mobileNum;
    // private ArrayList <Payment> bookingHistory;
    private List<MovieReview> reviewHistory;
    private final static long serialVersionUID = 9L;

    public MovieGoer(String UUID, String name, String email, String mobileNum) {
        this.name = name;
        this.UUID = UUID;
        this.email = email;
        this.mobileNum = mobileNum;
        // this.bookingHistory = new ArrayList<>();
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

    // public ArrayList<Payment> getBookingHistory() {
    // return bookingHistory;
    // }

    // public void setBookingHistory(ArrayList<Payment> bookingHistory) {
    // this.bookingHistory = bookingHistory;
    // }

    public List<MovieReview> getReviewHistory() {
        return reviewHistory;
    }

    public void setReviewHistory(List<MovieReview> reviewHistory) {
        this.reviewHistory = reviewHistory;
    }

    public abstract double getGoerPrice();
}