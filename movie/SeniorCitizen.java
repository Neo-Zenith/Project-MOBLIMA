public class SeniorCitizen extends MovieGoer{
    private int age;
    private double price;

    public SeniorCitizen(int userID, String name, String email, String mobileNum, int age){
        super(userID, name, email, mobileNum);
        this.age = age;
        this.price = 2.00;
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