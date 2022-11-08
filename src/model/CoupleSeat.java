package src.model;

import src.model.enums.*;
import src.database.*;

/**
 * @author Lee Juin
 * @version 1.0
 */
public class CoupleSeat extends Seat {
    private double seatPrice;
    private SeatType seatType;

    /**
     * Constructor for StandardSeat class
     * @param UUID is the unique ID in the database
     */
    public CoupleSeat(String UUID) {
        super(UUID);
        this.setSeatPrice(Database.PRICES.getDefaultSeatPrice());
        this.setSeatType(SeatType.COUPLE);
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
     * Gets the {@Link SeatType} of the seat
     * @return The {@Link SeatType} of the seat
     */
    public SeatType getSeatType() {
        return this.seatType;
    }   

    /**
     * Sets the {@Link SeatType} of the seat
     * @param seatType is the {@Link SeatType} of the seat
     */
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
