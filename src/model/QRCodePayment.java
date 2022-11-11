package src.model;

import src.model.enums.*;

/**
 * The Class inherited from the Superclass {@link Payment}
 * that handles the data of QR Code Payment
 * 
 * @author Ci Hui
 * @version 1.0
 */

public class QRCodePayment extends Payment {
    /**
     * {@link PaymentType} of the QR Code Payment
     */
    private PaymentType paymentType;

    /**
     * Price of the Movie Tickets
     */
    private double movieTicketPrice;

    /**
     * Constructor for QRCodePayment Class
     * 
     * @param UUID             is the unique ID in the database
     * @param paymentType      is the {@link PaymentType} of the QR Code Payment
     * @param transactionID    is the Transaction ID of the QR Code Payment
     * @param movieTicketPrice is the price of the Movie Tickets
     */
    public QRCodePayment(String UUID, PaymentType paymentType, String transactionID, double movieTicketPrice) {
        super(UUID, transactionID);
        this.setMovieTicketPrice(movieTicketPrice);
        this.setPaymentType(paymentType);
    }

    /**
     * Gets the {@link PaymentType} of the QR Code Payment
     * 
     * @return {@link PaymentType} of the QR Code Payment
     */
    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    /**
     * Sets the {@link PaymentType} of the QR Code Payment
     * 
     * @param paymentType is the {@link PaymentType} of the QR Code Payment to be
     *                    set
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the Price of the Movie Tickets
     * 
     * @return the price of the Movie Tickets
     */
    public double getMovieTicketPrice() {
        return this.movieTicketPrice;
    }

    /**
     * Sets the price of the Movie Tickets
     * 
     * @param movieTicketPrice is the price of the Movie Tickets to be set
     */
    public void setMovieTicketPrice(double movieTicketPrice) {
        this.movieTicketPrice = movieTicketPrice;
    }
}
