package controller;

import java.util.ArrayList;

import database.*;
import handler.*;
import model.*;
import model.enums.*;

public class SeatManager {

    public SeatManager() {}

    
    public static Seat createCoupleSeat() {
        String UUID = String.format("ST%04d", DatabaseHandler.generateUUID(Database.SEAT));
        Seat seat = new CoupleSeat(UUID);
        DatabaseManager.saveUpdateToDatabase(UUID, seat, Database.SEAT);
        return seat;
    }

    
    public static Seat createStandardSeat() {
        String UUID = String.format("ST%04d", DatabaseHandler.generateUUID(Database.SEAT));
        Seat seat = new StandardSeat(UUID);
        DatabaseManager.saveUpdateToDatabase(UUID, seat, Database.SEAT);
        return seat;
    }


    public static void printStandardCinemaFloorMap(ArrayList <Seat> seatingPlan) {
        int totalNumOfSeatsPerRow = Database.totalNumOfSeats / Database.numOfRows;
        int index = 0;
        System.out.print("    ");
        for (int i = 0; i < totalNumOfSeatsPerRow; i ++) {
            if (i == totalNumOfSeatsPerRow / 2) {
                System.out.print("      ");
            }
            System.out.print("[ " + String.format("%02d", i + 1) + "]");
        }

        System.out.println(""); 
        System.out.println(""); 

        Character row = 'A';
        for (int i = 0; i < Database.numOfCoupleRows; i ++) {
            System.out.print(row);
            System.out.print("   ");
            row ++;
            for (int j = 0; j < totalNumOfSeatsPerRow; j += 2) {
                Seat seat = seatingPlan.get(index);
                if (j == totalNumOfSeatsPerRow / 2) {
                    System.out.print("      ");
                }

                if (seat.getAssignStatus()) {
                    System.out.print("[   XX   ]");
                }
                else {
                    System.out.print("[        ]");
                }
                index += 2;
            }
            System.out.println("");
        }

        for (int i = 0; i < Database.numOfRows - Database.numOfCoupleRows; i ++) {
            System.out.print(row);
            System.out.print("   ");
            row ++;
            for (int j = 0; j < totalNumOfSeatsPerRow; j ++) {
                Seat seat = seatingPlan.get(index);
                if (j == totalNumOfSeatsPerRow / 2) {
                    System.out.print("      ");
                }

                if (seat.getAssignStatus()) {
                    System.out.print("[ X ]");
                }
                else {
                    System.out.print("[   ]");
                }
                index ++;
            }
            System.out.println("");
        }

        System.out.println("");
        System.out.println("""
                                                  Screen                                  
                """);
    }


    public static void printPlatinumCinemaFloorMap(ArrayList <Seat> seatingPlan) {
        int totalNumOfSeatsPerRow = Database.platinumNumOfSeatsPerRow;
        int numOfRows = Database.platinumNumOfRow;

        int index = 0;
        System.out.print("    ");
        for (int i = 0; i < totalNumOfSeatsPerRow; i ++) {
            if (i == totalNumOfSeatsPerRow / 2) {
                System.out.print("      ");
            }
            System.out.print("[  " + String.format("%02d", i + 1) + " ]");
        }

        System.out.println(""); 
        System.out.println(""); 

        Character row = 'A';
        for (int i = 0; i < numOfRows; i ++) {
            System.out.print(row);
            System.out.print("   ");
            row ++;
            for (int j = 0; j < totalNumOfSeatsPerRow; j ++) {
                Seat seat = seatingPlan.get(index);
                if (j == totalNumOfSeatsPerRow / 2) {
                    System.out.print("      ");
                }

                if (seat.getAssignStatus()) {
                    System.out.print("[  X  ]");
                }
                else {
                    System.out.print("[     ]");
                }
                index ++;
            }
            System.out.println("");
            System.out.println("");
        }

        System.out.println("");
        System.out.println("""
                          Screen                                  
                """);  
    }

    
    public static int seatIDConverter(String seatID, Cinema cinema) {
        int totalNumOfSeatsPerRow;
        if (cinema.getCinemaClass() == CinemaClass.PLATINUM) {
            totalNumOfSeatsPerRow = Database.platinumNumOfSeatsPerRow;
        }
        else {
            totalNumOfSeatsPerRow = Database.totalNumOfSeats / Database.numOfRows;
        }
        char row = seatID.charAt(0);
        int rowConverted = row - 65;
        int colConverted = Integer.parseInt(seatID.substring(1));

        if (colConverted > totalNumOfSeatsPerRow) {
            return -1;
        }
        int convertedSeatID = rowConverted * (totalNumOfSeatsPerRow) + colConverted - 1;
        return convertedSeatID;
    }

    
    public static boolean validateBooking(Seat seat, boolean booking) {
        if (seat.getAssignStatus() && booking) {
            return false;
        }
        return true;
    }

    
    public static boolean bookSeat(String seatID, MovieSchedule movieSchedule, Cinema cinema) {
        int index = SeatManager.seatIDConverter(seatID, cinema);

        int venueSlot = MovieScheduleManager.getShowingVenueIndex(movieSchedule, cinema);
        ArrayList <Seat> seatingPlan = movieSchedule.getSeatingPlan().get(venueSlot);

        Seat seatToBook = seatingPlan.get(index);
        
        if (seatToBook.getSeatType() == SeatType.STANDARD) {
            if (validateBooking(seatToBook, true)) {
                seatToBook.setAssignStatus(true);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            Seat nextSeat;
            int nextSeatIndex;

            if (index % 2 == 0) {
                nextSeatIndex = index + 1;
            }
            else {
                nextSeatIndex = index - 1;
            }
            nextSeat = seatingPlan.get(nextSeatIndex);

            if (validateBooking(seatToBook, true) && validateBooking(nextSeat, true)) {
                seatToBook.setAssignStatus(true);
                nextSeat.setAssignStatus(true);
                return true;
            }
            else {
                return false;
            }
        }
    }


    public static Seat getSeatBySeatID(String seatID, ArrayList <Seat> seatingPlan, Cinema cinema) {
        int index = SeatManager.seatIDConverter(seatID, cinema);
        return seatingPlan.get(index);
    }

    
    public static Seat getSeatByUUID(String seatUUID) {
        ArrayList <Seat> seats = Database.getValueList(Database.SEAT.values());

        for (int i = 0; i < seats.size(); i ++) {
            Seat seat = seats.get(i);
            if (seat.getUUID().equals(seatUUID)) {
                return seat;
            }
        }
        return null;
    }
}
