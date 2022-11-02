package model;

import model.enums.MovieGoerAge;

public class SeniorCitizen extends MovieGoer {
    private MovieGoerAge age;
    private double price;

    public SeniorCitizen(String UUID, String name, String email, String mobileNum, MovieGoerAge age, String username, String password) {
        super(UUID, name, email, mobileNum, username, password);
        this.age = age;
        this.price = 2.00;
    }

    public MovieGoerAge getMovieGoerAge() {
        return this.age;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGoerPrice() {
        return this.price;
    }
}