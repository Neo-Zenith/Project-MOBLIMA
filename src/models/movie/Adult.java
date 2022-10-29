package models.movie;

public class Adult extends MovieGoer {
    private int age;
    private double price;

    public Adult(int userID, String name, String email, String mobileNum, int age) {
        super(userID, name, email, mobileNum);
        this.age = age;
        this.price = 5.00;
    }

    public int getAge() {
        return this.age;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

}