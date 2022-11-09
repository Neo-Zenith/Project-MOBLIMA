package src.model;

import java.io.Serializable;
import java.util.*;
import src.model.enums.*;

/**
 * The Class that handles the data of Movie Goer
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */
public abstract class MovieGoer implements Serializable {
    /**
     * Name of the Movie Goer
     */
    private String name;

    /**
     * Unique ID of the Movie Goer in database
     */
    private String UUID;

    /**
     * Email address of the Movie Goer
     */
    private String email;

    /**
     * Mobile Number of the Movie Goer
     */
    private String mobileNum;

    /**
     * Username of the Movie Goer in MOBLIMA
     */
    private String username;

    /**
     * Password of the Movie Goer in MOBLIMA
     */
    private String password;

    /**
     * All the {@link BookingHistory} of the Movie Goer
     */
    private ArrayList<BookingHistory> bookingHistory;

    /**
     * All the {@link MovieReview} set by the Movie Goer
     */
    private ArrayList<MovieReview> reviewHistory;

    /**
     * For Java serializable
     */
    private final static long serialVersionUID = 9L;

    /**
     * Constructor for MovieGoer class
     * 
     * @param UUID      is the unique ID of Movie Goer in database
     * @param name      is the name of Movie Goer
     * @param email     is the email address of Movie Goer
     * @param mobileNum is the mobile number of Movie Goer
     * @param username  is the username of Movie Goer in MOBLIMA
     * @param password  is the password of Movie Goer to login MOBLIMA
     */
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

    /**
     * Gets the username of the Movie Goer
     * 
     * @return username of Movie Goer
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of the Movie Goer
     * 
     * @param username is the username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the Movie Goer
     * 
     * @return password of the Movie Goer
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password of Movie Goer's account
     * 
     * @param password is the password of Movie Goer's account to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the name of the Movie Goer
     * 
     * @return name of the Movie Goer
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the Movie Goer
     * 
     * @param name is the name of Movie Goer to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets unique ID of Movie Goer in database
     * 
     * @return unique ID of the Movie Goer
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Sets the unique ID of the Movie Goer
     * 
     * @param UUID is the unique ID of Movie Goer to be set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the email address of the Movie Goer
     * 
     * @return email address of the Movie Goer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the Movie Goer
     * 
     * @param email is the email address of Movie Goer to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the mobile number of the Movie Goer
     * 
     * @return mobile number of the Movie Goer
     */
    public String getMobileNum() {
        return mobileNum;
    }

    /**
     * Sets the mobile number of the Movie Goer
     * 
     * @param mobileNum is the mobile number of Movie Goer to be set
     */
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    /**
     * Gets all the {@link BookingHistory} made by the Movie Goer
     * 
     * @return ArrayList of {@link BookingHistory} of the Movie Goer
     */
    public ArrayList<BookingHistory> getBookingHistory() {
        return this.bookingHistory;
    }

    /**
     * Sets all the {@link BookingHistory} made by the Movie Goer
     * 
     * @param bookingHistory is the ArrayList of {@link BookingHisotry} to be set
     */
    public void setBookingHistory(ArrayList<BookingHistory> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    /**
     * Adds booking history of the Movie Goer
     * 
     * @param history is the {@link BookingHistory} to be added
     */
    public void addBookingHistory(BookingHistory history) {
        this.bookingHistory.add(history);
    }

    /**
     * Gets all the {@link MovieReview} made by the Movie Goer
     * 
     * @return ArrayList of {@link MovieReview} made by the Movie Goer
     */
    public ArrayList<MovieReview> getReviewHistory() {
        return reviewHistory;
    }

    /**
     * Sets all the {@link MovieReview} made by the Movie Goer
     * 
     * @param reviewHistory is the ArrayList of {@link MovieReview} to be set
     */
    public void setReviewHistory(ArrayList<MovieReview> reviewHistory) {
        this.reviewHistory = reviewHistory;
    }

    /**
     * Adds {@link MovieReview} to the Movie Goer's review history
     * 
     * @param movieReview is the {@link MovieReview} to be added
     */
    public void addReviewHistory(MovieReview movieReview) {
        this.reviewHistory.add(movieReview);
    }

    /**
     * Gets the Movie Goer Age Group
     * 
     * @return {@link MovieGoerAge} of the MovieGoer
     */
    public abstract MovieGoerAge getMovieGoerAge();

    /**
     * Gets the price weight of the Movie Goer
     * 
     * @return The price weight of the Movie Goer
     */
    public abstract double getGoerPrice();

    /**
     * Sets the price weight of the Movie Goer
     * 
     * @param price is the price weight of the Movie Goer
     */
    public abstract void setGoerPrice(double price);
}