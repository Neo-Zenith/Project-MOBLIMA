package models.cinema.printer.seat_printer;

import models.cinema.seat_type.Seat;

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
