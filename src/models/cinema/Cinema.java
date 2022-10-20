package models;
import java.util.*;

public class Cinema {
    private int cinemaID;
    private int totalNumOfSeats;
    private int numOfRows;
    private int numOfSeatsPerRow[];
    private float cinemaPrice;
    private CinemaType cinemaType;
    private Seat seat[][];

    public Cinema(  int cinemaID, 
                    int totalNumOfSeats, 
                    CinemaType cinemaType, 
                    float cinemaPrice,
                    int numOfRows,
                    int numOfSeatsPerRow[]) {

        this.cinemaType = cinemaType;
        this.cinemaID = cinemaID;
        this.totalNumOfSeats = totalNumOfSeats;
        this.cinemaPrice = cinemaPrice;
        this.numOfRows = numOfRows;
        this.numOfSeatsPerRow = numOfSeatsPerRow;
        this.seat = new Seat[numOfRows][];
    }

    /* 
     * Get method that returns an ID of a cinema
     */
    public int getCinemaID() {
        return this.cinemaID;
    }

    /*
     * Get method that returns the total number of seats in a cinema
     */
    public int getTotalNumOfSeats() {
        return this.totalNumOfSeats;
    }

    /*
     * Set method to modify the total number of seats in a cinema
     */
    public void setTotalNumOfSeats(int totalNumOfSeats) {
        this.totalNumOfSeats = totalNumOfSeats;
    }

    public int getNumOfRows() {
        return this.numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public CinemaType getCinemaType() {
        return this.cinemaType;
    }

    public void setCinemaType(CinemaType cinemaType) {
        this.cinemaType = cinemaType;
    } 

    /*
     * Get method that returns the cinema's weightage on ticket price
     */
    public float getCinemaPrice() {
        return this.cinemaPrice;
    }

    /*
     * Set method to set the cinema's weightage on ticket price
     */
    public void setCinemaPrice(float cinemaPrice) {
        this.cinemaPrice = cinemaPrice;
    }

    /*
     * Populates the seats in a cinema with different types of seats and different prices
     * numOfRowsPerSeatType[] is an array of integers representing the number of rows for each
     *      seat type
     * pricePerSeatType[] is an array of float representing the price for each seat type
     * seatType[] is an array that represents the seat type.
     * 
     * Assume that each row has the same seat type.
     * Assume that rows of the same seat type are always together.
     */
    public void populateSeat(   int numOfRowsPerSeatType[], 
                                float pricePerSeatType[], 
                                List <SeatType> seatType) {

        int index = 0;
        for (int i = 0; i < numOfRowsPerSeatType.length; i ++) {
            for (int j = 0; j < numOfRowsPerSeatType[i]; j ++) {
                this.seat[index + j] = new Seat[this.numOfSeatsPerRow[index + j]];
                for (int k = 0; k < this.numOfSeatsPerRow[index + j]; k ++)
                {
                    this.seat[index + j][k] = new Seat( index + j, k, 
                                                        seatType.get(i), pricePerSeatType[i]);
                }
            }
            index += numOfRowsPerSeatType[i];
        }
    }

    public Seat getSeat(int rowId, int columnId) {
        return this.seat[rowId][columnId];
    }

    public void printFloorMap() {
        int maxSeatsPerRow = this.getMaxRowSize();

        System.out.println("Floor map for cinema ID " + this.cinemaID);
        for (int i = 0; i < this.numOfRows; i ++) {
            System.out.print(String.format("Row %02d   ", i));

            int aisleBreak = (this.numOfSeatsPerRow[i] % 2 == 0) ? 
                                this.numOfSeatsPerRow[i] / 2: this.numOfSeatsPerRow[i] / 2 + 1;

            if (this.seat[i][0].getSeatType() == SeatType.STANDARD) {
                for (int j = 0; j < (maxSeatsPerRow - aisleBreak); j ++) {
                    System.out.print("      ");
                }
            }
            else {
                for (int j = 0; j < (maxSeatsPerRow - aisleBreak * 2); j ++) {
                    System.out.print("      ");
                }
            }
            
            for (int j = 0; j < this.numOfSeatsPerRow[i]; j ++) {
                if (j == aisleBreak) {
                    System.out.print("      ");
                }

                if (this.seat[i][j].getSeatType() == SeatType.STANDARD) {
                    if (this.seat[i][j].checkIfAssigned()) {
                        System.out.print("[ XX ]");
                    }
                    else {
                        System.out.print(String.format("[ %02d ]", j));
                    }
                }

                else if (this.seat[i][j].getSeatType() == SeatType.COUPLE) {
                    if (this.seat[i][j].checkIfAssigned()) {
                        System.out.print("[    XX    ]");
                    }
                    else {
                        System.out.print(String.format("[    %02d    ]", j));
                    }
                }
            }
            System.out.println("");
        }
    }

    public int getMaxRowSize() {
        int max = 0;

        for (int i = 0; i < this.numOfRows; i ++) {
            int rowBreak = (this.numOfSeatsPerRow[i] % 2 == 0) ? 
                            this.numOfSeatsPerRow[i] / 2: this.numOfSeatsPerRow[i] / 2 + 1;

            if (this.seat[i][0].getSeatType() == SeatType.STANDARD 
                && rowBreak > max) {
                    max = rowBreak;
                }
            
            else if (this.seat[i][0].getSeatType() == SeatType.COUPLE 
                    && rowBreak * 2 > max) {
                max = rowBreak * 2;
            }
        }
        return max;
    }
    
}
