package models.printer.seat_printer;

import models.seat.Seat;

public class StandardSeatPrinter implements ISeatPrinter{
    public void printSeatFigure(Seat seat) {
        if (seat.getSeatAssignStatus()) {
            System.out.print("[X]");
        }
        else {
            System.out.print("[ ]");
        }
        
    }
}
