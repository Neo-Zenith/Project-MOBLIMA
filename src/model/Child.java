package model;

import model.enums.MovieGoerAge;

public class Child extends MovieGoer {
    private MovieGoerAge age;
    private double price;

    public Child(String UUID, String name, String email, String mobileNum) {
        super(UUID, name, email, mobileNum);
        this.age = MovieGoerAge.Child;
        this.price = 4.00;
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