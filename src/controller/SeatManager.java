package controller;


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
}
