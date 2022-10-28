package models.cinema.seat_type;

public abstract class Seat {
    public int seatID;
    public boolean isAssigned;
    public double seatPrice;

    /*
     * Constructor
     */
    public Seat(int seatID) {
        this.seatID = seatID;
        this.isAssigned = false;
    }

    /*
     * Returns the seat ID
     */
    public int getSeatID() {
        return this.seatID;
    }

    /* 
     * Checks if the seat has been assigned
     */
    public boolean getSeatAssignStatus() {
        return this.isAssigned;
    }

    /*
     * Assigns or unassigns the seat
     */
    public void setSeatAssignStatus(boolean assign) {
        this.isAssigned = assign;
    }

    /*
     * Returns the seat price
     */
    public double getSeatPrice() {
        return this.seatPrice;
    }

    /*
     * Abstract method to set the seat price based on different seat type
     */
    public abstract void setSeatPrice(double price, double discount, boolean onDiscount);

    public abstract int printSeatFigure();
}
