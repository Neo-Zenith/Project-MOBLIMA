package src.model;

import java.io.Serializable;
import src.model.enums.*;


/**
 * @author Lee Juin
 * @version 1.0
 */
public abstract class Seat implements Serializable{
    private String UUID;
    private boolean isAssigned;
    private static final long serialVersionUID = 3L;
    
    /**
     * Constructor for Seat class
     * @param UUID is the unique ID in the database
     */
    public Seat(String UUID) {
        this.setUUID(UUID);
        this.isAssigned = false;
    }

    /**
     * Gets the unique ID of the seat
     * @return The unique ID of the seat
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Sets the unique ID of the seat
     * @param UUID is the unique ID of the seat
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the {@Link SeatType} of the seat
     * @return The {@link SeatType} of the seat
     */
    public abstract SeatType getSeatType();

    /**
     * Sets the {@Link SeatType} of the seat
     * @param seatType is the {@link SeatType} of the seat
     */
    public abstract void setSeatType(SeatType seatType);

    /**
     * Gets the seat assignment status
     * @return {@code true} if seat is assigned, {@code false} otherwise
     */
    public boolean getAssignStatus() {
        return this.isAssigned;
    }

    /**
     * Sets the seat assignment status
     * @param assign is the assignment status
     */
    public void setAssignStatus(boolean assign) {
        this.isAssigned = assign;
    }

    /**
     * Gets the price weight of the seat
     * @return The price weight of the seat
     */
    public abstract double getSeatPrice();

    /**
     * Sets the price weight of the seat
     * @param seatPrice is the price weight of the seat
     */
    public abstract void setSeatPrice(double seatPrice);
}
