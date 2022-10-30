package models.seat;

import models.printer.seat_printer.CoupleSeatPrinter;

public class CoupleSeat extends Seat{
    public int seatID;
    public boolean isAssigned;
    public double seatPrice;

    /*
     * Constructor
     */
    public CoupleSeat(int seatID) {
        super(seatID);
    }

    /*
     * Concrete implementation of abstract method setSeatPrice for class StandardSeat
     */
    public void setSeatPrice(double price, double discount, boolean onDiscount) {
        if (onDiscount) {
            this.seatPrice = price - discount;
        }   
        else {
            this.seatPrice = price;
        }
    }

    public int printSeatFigure() {
        CoupleSeatPrinter printer = new CoupleSeatPrinter();
        printer.printSeatFigure(this);
        return 1;
    }
}
