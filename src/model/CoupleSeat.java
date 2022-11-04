
package model;

import model.enums.SeatType;

public class CoupleSeat extends Seat {
    private double seatPrice;

    public CoupleSeat(String UUID, SeatType seatType, boolean isAssigned, double seatPrice) {
        super(UUID, seatType, isAssigned, seatPrice);
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }
}
