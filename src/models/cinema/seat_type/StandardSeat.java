package models.cinema.seat_type;

import models.cinema.printer.seat_printer.StandardSeatPrinter;

public class StandardSeat extends Seat{
    public int seatID;
    public boolean isAssigned;
    public double seatPrice;

    /*
     * Constructor
     */
    public StandardSeat(int seatID) {
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
        StandardSeatPrinter printer = new StandardSeatPrinter();
        printer.printSeatFigure(this);
        return 0;
    }
}
