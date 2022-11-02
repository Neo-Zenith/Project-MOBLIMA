package controller;

import java.util.ArrayList;

import database.Database;
import handler.DatabaseHandler;
import model.CoupleSeat;
import model.Seat;
import model.StandardSeat;
import model.enums.SeatType;

public class SeatManager {

    public SeatManager() {}

    /**
     * Method to instantiate a CoupleSeat instance and save to database
     * @param seatType {@link SeatType} of the seat, uses {@code SeatType.COUPLE}
     * @param seatPrice price weight of the seat
     * @return {@link Seat} object that was created
     */
    public static Seat createCoupleSeat(SeatType seatType, double seatPrice) {
        String UUID = String.format("CS%03d", DatabaseHandler.generateUUID(Database.SEAT));
        Seat seat = new CoupleSeat(UUID, seatType, false, seatPrice);
        DatabaseManager.saveUpdateToDatabase(UUID, seat, Database.SEAT);
        return seat;
    }

    /**
     * Method to instantiate a StandardSeat instance and save to database
     * @param seatType {@link SeatType} of the seat, uses {@code SeatType.STANDARD}
     * @param seatPrice price weight of the seat
     * @return {@link Seat} object that was created
     */
    public static Seat createStandardSeat(SeatType seatType, double seatPrice) {
        String UUID = String.format("SS%03d", DatabaseHandler.generateUUID(Database.SEAT));
        Seat seat = new StandardSeat(UUID, seatType, false, seatPrice);
        DatabaseManager.saveUpdateToDatabase(UUID, seat, Database.SEAT);
        return seat;
    }


    public static void printCinemaFloorMap(ArrayList <Seat> seatingPlan) {
        int totalNumOfSeatsPerRow = Database.totalNumOfSeats / Database.numOfRows;

        int index = 0;
        for (int i = 0; i < Database.numOfCoupleRows; i ++) {
            for (int j = 0; j < totalNumOfSeatsPerRow; j += 2) {
                Seat seat = seatingPlan.get(index);
                if (j == totalNumOfSeatsPerRow / 2) {
                    System.out.print("      ");
                }

                if (seat.getAssignStatus()) {
                    System.out.print("[ XX ]");
                }
                else {
                    System.out.print("[    ]");
                }
                index += 2;
            }
            System.out.println("");
        }

        index = 0;
        for (int i = 0; i < Database.numOfRows - Database.numOfCoupleRows; i ++) {
            for (int j = 0; j < totalNumOfSeatsPerRow; j ++) {
                Seat seat = seatingPlan.get(index);
                if (j == totalNumOfSeatsPerRow / 2) {
                    System.out.print("      ");
                }

                if (seat.getAssignStatus()) {
                    System.out.print("[X]");
                }
                else {
                    System.out.print("[ ]");
                }
                index ++;
            }
            System.out.println("");
        }
    }
}
