package models.printer;

import models.cinema.Cinema;

public class CinemaFloorMapPrinter{
    public Cinema cinema;

    public CinemaFloorMapPrinter(Cinema cinema) {
        this.cinema = cinema;
    }
 
    public void print(Cinema cinema) {
        System.out.println("Cinema " + cinema.getCinemaID());
        System.out.println();
        System.out.println();

        int totalNumOfSeats = cinema.getTotalNumOfSeats();
        int totalRows = cinema.getTotalRows();
        int totalSeatsPerRow = totalNumOfSeats / totalRows;

        int index = 0;
        int increment;
        for (int i = 0; i < totalRows; i ++) {
            for (int j = 0; j < totalSeatsPerRow; j ++) {
                if (j == totalSeatsPerRow / 2) {
                    System.out.print("   ");
                }
                increment = cinema.getSeats()[index].printSeatFigure();
                j += increment;
                index += increment;
            }
            System.out.println();
        }
    }
}
