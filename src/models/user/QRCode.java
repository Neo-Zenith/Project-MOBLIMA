package models.user;

// if want to generate QRcode in terminal : https://github.com/sorleone/TermQRCode

public class QRCode extends Payment {
    public QRCode(String transactionID, String movieTitle, int seatRowID, int seatColumnID, double finalPrice){
        super(transactionID, movieTitle, seatRowID, seatColumnID, finalPrice);
    }
}
