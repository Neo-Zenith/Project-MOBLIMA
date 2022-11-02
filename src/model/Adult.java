package model;

public class Adult extends MovieGoer {
    private int age;
    private double price;

    public Adult(String UUID, String name, String email, String mobileNum, int age, String username, String password) {
        super(UUID, name, email, mobileNum, username, password);
        this.age = age;
        this.price = 5.00;
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