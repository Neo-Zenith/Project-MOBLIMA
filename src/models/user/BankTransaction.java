package models.user;

public class BankTransaction extends Payment{

    private int bankAccountNumber;
    private int OTP;

    public BankTransaction(String transactionID, String movieTitle, int seatRowID, int seatColumnID, double finalPrice, int bankAccountNumber, int OTP){
        super(transactionID, movieTitle, seatRowID, seatColumnID, finalPrice);
        this.bankAccountNumber = bankAccountNumber;
        this.OTP = OTP;
    }

    public int getBankAccountNumber(){
        return this.bankAccountNumber;
    }

    public int getOTP(){
        return this.OTP;
    }

    public void setBankAccountNumber(int bankAccountNumber){
        this.bankAccountNumber = bankAccountNumber;
    }

    public void setOTP(int OTP){
        this.OTP = OTP;
    }
}
