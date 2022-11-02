package model;

import model.enums.PaymentType;

public class QRCodePayment extends Payment{
    
    private String UUID;
    private PaymentType paymentType;
    private String transactionID;
    private double movieTicketPrice;

    public QRCodePayment(String UUID, PaymentType paymentType, String transactionID, double movieTicketPrice) {
        super(UUID, paymentType, transactionID, movieTicketPrice);
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setMovieTicketPrice(double movieTicketPrice) {
        this.movieTicketPrice = movieTicketPrice;
    }
}
