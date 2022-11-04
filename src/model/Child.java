package model;

import model.enums.MovieGoerAge;

public class Child extends MovieGoer {
    private MovieGoerAge age;
    private double price;

    public Child(String UUID, String name, String email, String mobileNum, String username, String password) {
        super(UUID, name, email, mobileNum, username, password);
        this.age = MovieGoerAge.Student;
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