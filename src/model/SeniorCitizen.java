package model;

public class SeniorCitizen extends MovieGoer {
    private int age;
    private double price;

    public SeniorCitizen(String UUID, String name, String email, String mobileNum, int age) {
        super(UUID, name, email, mobileNum);
        this.age = age;
        this.price = 2.00;
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