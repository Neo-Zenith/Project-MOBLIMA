package models.user;

public class Card extends Payment{

    private String cardNumber;
    private int cardCCV;
    private String cardHolderName;
    private int OTP;

    public Card (String transactionID, String movieTitle, int seatRowID, int seatColumnID, double finalPrice, String cardNumber, int cardCCV, String cardHolderName, int OTP){
        super(transactionID, movieTitle, seatRowID, seatColumnID, finalPrice); 
        this.cardNumber = cardNumber;
        this.cardCCV = cardCCV;
        this.cardHolderName = cardHolderName;
        this.OTP = OTP;
    }

    public String getCardNumber(){
        return this.cardNumber;
    }

    public int getCardCCV(){
        return this.cardCCV;
    }

    public String getCardHolderName(){
        return this.cardHolderName;
    } 

    public int getOTP(){
        return this.OTP;
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

    public void setCardCCV(int cardCCV){
        this.cardCCV = cardCCV;
    }

    public void setCardHolderName(String cardHolderName){
        this.cardHolderName = cardHolderName;
    } 

    public void setOTP(int OTP){
        this.OTP = OTP;
    }

}
