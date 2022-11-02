package model;

public class Child extends MovieGoer {
    private int age;
    private double price;

    public Child(String UUID, String name, String email, String mobileNum, int age, String username) {
        super(UUID, name, email, mobileNum, username);
        this.age = age;
        this.price = 4.00;
    }

    public int getAge() {
        return this.age;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGoerPrice() {
        return this.price;
    }

}