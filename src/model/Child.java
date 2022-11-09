package src.model;

import src.database.*;
import src.model.enums.*;

/**
 * The Class that handles the data of Child Movie Goer
 * 
 * @author Jerick, Yeek Sheng
 * @version 1.0
 */
public class Child extends MovieGoer {
    /**
     * Age group of the Movie Goer
     */
    private MovieGoerAge age;

    /**
     * Price weight of the Movie Goer
     */
    private double price;

    /**
     * Constructor for Child class
     * 
     * @param UUID      is the unique ID in the database
     * @param name      is the name of Movie Goer
     * @param email     is the email of Movie Goer
     * @param mobileNum is the mobile number of Movie Goer
     * @param username  is the unique username for user to login MOBLIMA
     * @param password  is the password for user to login MOBLIMA
     */
    public Child(String UUID, String name, String email, String mobileNum, String username, String password) {
        super(UUID, name, email, mobileNum, username, password);
        this.age = MovieGoerAge.Student;
        this.price = Database.PRICES.getDefaultChildPrice();
    }

    /**
     * Gets the Movie Goer Age Group
     * 
     * @return {@link MovieGoerAge} of the MovieGoer
     */
    public MovieGoerAge getMovieGoerAge() {
        return this.age;
    }

    /**
     * Sets the price weight of the Movie Goer
     * 
     * @param price is the price weight of the Movie Goer
     */
    public void setGoerPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the price weight of the Movie Goer
     * 
     * @return The price weight of the Movie Goer
     */
    public double getGoerPrice() {
        return this.price;
    }

}