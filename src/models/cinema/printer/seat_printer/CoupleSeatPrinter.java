package models.cinema.printer.seat_printer;

import models.cinema.seat_type.Seat;

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
