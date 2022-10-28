public class Child extends MovieGoer{
    private int age;
    private double price;

    public Child(int userID, String name, String email, String mobileNum, int age){
        super(userID, name, email, mobileNum);
        this.age = age;
        this.price = 4.00;
    }

    public int getAge(){
        return this.age;
    }
    public double setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return this.price;
    }
}