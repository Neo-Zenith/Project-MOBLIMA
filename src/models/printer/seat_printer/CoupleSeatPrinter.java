package models.printer.seat_printer;

import models.seat.Seat;

public class CoupleSeatPrinter implements ISeatPrinter{
    public void printSeatFigure(Seat seat) {
        if(seat.getSeatAssignStatus()) {
            System.out.print("[ XX ]");
        }
        else {
            System.out.print("[    ]");
        }
    }
}
