package models;

public class Seat {
    private int rowID;
    private int columnID;
    private boolean isAssigned;
    private float seatPrice;
    private SeatType seatType;

    public Seat(int rowID, int columnID, SeatType seatType, float seatPrice) {
        this.rowID = rowID;
        this.columnID = columnID;
        this.seatType = seatType;
        this.isAssigned = false;
        this.seatPrice = seatPrice;
    }

    public int getRowID() {
        return this.rowID;
    }

    public int getColumnID() {
        return this.columnID;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public boolean checkIfAssigned() {
        return this.isAssigned;
    }

    public float getSeatPrice() {
        return this.seatPrice;
    }

    public void setSeatPrice(float seatPrice) {
        this.seatPrice = seatPrice;
    }
}
