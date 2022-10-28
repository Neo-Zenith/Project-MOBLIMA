package models;
import java.util.*;

import models.cinema.*;
import models.cinema.cinema_type.IMaxCinema;
import models.cinema.printer.CinemaFloorMapPrinter;
import models.cinema.seat_type.CoupleSeat;
import models.cinema.seat_type.Seat;
import models.cinema.seat_type.StandardSeat;

/*
 * Testbench file. Feel free to test your models here.
 */
public class Testbench {
    public static void main(String [] args) {
        /*
         * Modify the code here to test your models
         */
        // TestCinemaCineplexSeat();
        TestCinemaCineplexSeat();
    }


    /*
     * Create your own testbench function and run on main()
     */
    public static void TestCinemaCineplexSeat() {
        Cineplex cathay = new Cineplex("Cathay", 3);
        Cinema cinema[] = new Cinema[3];

        for (int i = 0; i < cathay.numOfCinema; i ++) {
            cinema[i] = new IMaxCinema(i, 5, 20);
        }

        cathay.populateCineplex(cinema);
        System.out.println(cathay.getListofCinema()[0].getTotalNumOfSeats());
        
        
        for (int i = 0; i < cathay.numOfCinema; i ++) {
            int totalNumOfSeats = cathay.getListofCinema()[i].getTotalNumOfSeats();
            int totalRows = cathay.getListofCinema()[i].getTotalRows();
            Seat seat[] = new Seat[totalNumOfSeats];
            int numOfRowPerSeatType[] = new int[2];
            numOfRowPerSeatType[0] = 2;
            numOfRowPerSeatType[1] = 3;
            
            for (int j = 0; j < totalNumOfSeats; j ++) {
                if (j < totalNumOfSeats / totalRows * numOfRowPerSeatType[0]) {
                    seat[j] = new CoupleSeat(j);
                }
                else {
                    seat[j] = new StandardSeat(j);
                }
            }

            cathay.getListofCinema()[i].populateCinema(seat, numOfRowPerSeatType);
        }

        CinemaFloorMapPrinter floorMapPrinter = new CinemaFloorMapPrinter(cathay.getListofCinema()[0]);
        floorMapPrinter.print(cathay.getListofCinema()[0]);
    }
}