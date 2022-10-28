package models.cinema;

import models.cinema.seat_type.Seat;

public abstract class Cinema {
    
    public int cinemaID;
    public int totalNumOfSeats;
    public int totalRows;
    public double cinemaPrice;
    public Seat seat[];

    /*
     * Constructor
     */
    public Cinema(int cinemaID, int totalNumOfSeats, int totalRows) {
        this.cinemaID = cinemaID;
        this.totalNumOfSeats = totalNumOfSeats;
        this.totalRows = totalRows;
        this.seat = new Seat[this.totalNumOfSeats];
    }

    /*
     * Abstract method that calculates the price weight based on different cinema classes
     */
    public abstract void setCinemaClassPrice(double defaultPrice, double discount, boolean onDiscount);

    /*
     * Returns the price weight based on different cinema classes
     */
    public double getCinemaClassPrice() {
        return this.cinemaPrice;
    }

    /*
     * Returns the cinema ID
     */
    public int getCinemaID() {
        return this.cinemaID;
    }

    /*
     * Returns the total number of seats in the cinema
     */
    public int getTotalNumOfSeats() {
        return this.totalNumOfSeats;
    }

    /*
     * Returns the total number of rows of seats in the cinema
     */
    public int getTotalRows() {
        return this.totalRows;
    }

    /*
     * Sets the total number of seats in the cinema
     */
    public void setTotalNumberOfSeats(int totalNumOfSeats) {
        this.totalNumOfSeats = totalNumOfSeats;
    }

    /*
     * Sets the total number of rows of seats in the cinema
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    /*
     * Populates the cinema with the correct type of seats
     */
    public void populateCinema(Seat seat[], int numOfRowPerSeatType[]) {
        int numOfSeatPerRow = this.totalNumOfSeats / this.totalRows;

        int index = 0;
        for (int i = 0; i < numOfRowPerSeatType.length; i ++) {
            for (int j = 0; j < numOfRowPerSeatType[i]; j ++) {
                for (int k = 0; k < numOfSeatPerRow; k ++) {
                    this.seat[index] = seat[index];
                    index ++;
                }
            }
        }
    }

    /*
     * Returns the list of seats
     */
    public Seat[] getSeats() {
        return this.seat;
    }
}
