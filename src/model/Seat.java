package model;

import model.enums.SeatType;

public abstract class Seat {
    private String UUID;
    private SeatType seatType;
    private boolean isAssigned;
    private double seatPrice;
    
    public Seat(String UUID, SeatType seatType, boolean isAssigned, double seatPrice) {
        this.setUUID(UUID);
        this.setSeatType(seatType);
        this.setAssignStatus(isAssigned);
        this.setSeatPrice(seatPrice);
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public boolean getAssignStatus() {
        return this.isAssigned;
    }

    public void setAssignStatus(boolean assign) {
        this.isAssigned = assign;
    }

    public double getSeatPrice() {
        return this.seatPrice;
    }

    public abstract void setSeatPrice(double seatPrice);
}
