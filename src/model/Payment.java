package model;

import java.io.Serializable;

import model.enums.PaymentType;

public abstract class Payment implements Serializable {
    private String UUID;
    private PaymentType paymentType;
    private String transactionID;
    private double movieTicketPrice;
    private static final long serialVersionUID = 16L;

    public Payment(String UUID, PaymentType paymentType, String transactionID, double movieTicketPrice) {
        this.setUUID(UUID);
        this.setPaymentType(paymentType);
        this.setTransactionID(transactionID);
        this.setMovieTicketPrice(movieTicketPrice);
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public abstract void setTransactionID(String transactionID);

    public double getMovieTicketPrice() {
        return this.movieTicketPrice;
    }

    public abstract void setMovieTicketPrice(double movieTicketPrice);
}   
