package model;

import model.enums.SeatType;

public class StandardSeat extends Seat {
    private double seatPrice;

    public StandardSeat(String UUID, SeatType seatType, boolean isAssigned, double seatPrice) {
        super(UUID, seatType, isAssigned, seatPrice);
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }
}
