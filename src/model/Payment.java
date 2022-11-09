package src.model;

import java.io.Serializable;

import src.model.enums.*;

/**
 * The Class that handles the data of Payment
 * 
 * @author Ci Hui
 * @version 1.0
 */

public abstract class Payment implements Serializable {
    /**
     * Unique ID of the Payment
     */
    private String UUID;

    /**
     * Transaction ID of the Payment generated when MovieGoer selects payment
     */
    private String transactionID;

    /**
     * For Java Serializable
     */
    private static final long serialVersionUID = 16L;

    /**
     * Constructor for Payment class
     * 
     * @param UUID           is the unique ID in the database
     * @param transactionID  is the Transaction ID of the Payment
     */    
    public Payment(String UUID, String transactionID) {
        this.setUUID(UUID);
        this.setTransactionID(transactionID);
    }

    /**
     * Gets the unique ID of the Payment
     * 
     * @return unique ID of the Payment
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Set the unique ID of the Payment
     * 
     * @param UUID is the unique ID of the Payment to be set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the Payment Type of the Payment
     * 
     * @return {@link PaymentType} of the Payment
     */
    public abstract PaymentType getPaymentType();

    /**
     * Sets the Payment Type of the Payment
     * 
     * @param paymentType is the {@link PaymentType} to be set
     */
    public abstract void setPaymentType(PaymentType paymentType);

    /**
     * Gets the Transaction ID of the Payment
     * 
     * @return the Transaction ID of the Payment
     */
    public String getTransactionID() {
        return this.transactionID;
    }

    /**
     * Sets the TransactionID of the Payment
     * 
     * @param transactionID is the Trasaction ID of the Payment to be set
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Gets the price of the Movie Ticket
     * 
     * @return the price of the Movie Ticket
     */
    public abstract double getMovieTicketPrice();

    /**
     * Sets the price of the Movie Ticket
     * 
     * @param movieTicketPrice is the price of the Movie Ticket to be set
     */
    public abstract void setMovieTicketPrice(double movieTicketPrice);
}   
