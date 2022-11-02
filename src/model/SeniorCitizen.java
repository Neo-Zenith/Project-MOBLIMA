package model;

import model.enums.MovieGoerAge;

public class SeniorCitizen extends MovieGoer {
    private MovieGoerAge age;
    private double price;

    public SeniorCitizen(String UUID, String name, String email, String mobileNum) {
        super(UUID, name, email, mobileNum);
        this.age = MovieGoerAge.SeniorCitizen;
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