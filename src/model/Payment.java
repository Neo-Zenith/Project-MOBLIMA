package model;

import model.enums.PaymentType;

public abstract class Payment {
    private String UUID;
    private PaymentType paymentType;
    private String transactionID;
    private double movieTicketPrice;

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
