package src.model;

import src.model.enums.*;
import src.database.*;

/**
 * @author Lee Juin
 * @version 1.0
 */
public class StandardSeat extends Seat {
    private double seatPrice;
    private SeatType seatType;

    /**
     * Constructor for StandardSeat class
     * @param UUID is the unique ID in the database
     */
    public StandardSeat(String UUID) {
        super(UUID);
        this.setSeatPrice(Database.PRICES.getDefaultSeatPrice());
        this.setSeatType(SeatType.STANDARD);
    }

    /**
     * Sets the price weight of the seat
     * @param seatPrice is the price weight of the seat
     */
    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    /**
     * Gets the price weight of the seat
     * @return The price weight of the seat
     */
    public double getSeatPrice() {
        return this.seatPrice;
    }

    /**
     * Gets the {@link SeatType} of the seat
     * @return The {@link SeatType} of the seat
     */
    public SeatType getSeatType() {
        return this.seatType;
    }

    /**
     * Sets the {@link SeatType} of the seat
     * @param seatType is the {@link SeatType} of the seat
     */
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
