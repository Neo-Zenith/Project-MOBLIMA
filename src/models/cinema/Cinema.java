package models.cinema;

import models.seat.Seat;

public abstract class Cinema {
    
    public int cinemaID;
    public int totalNumOfSeats;
    public int totalRows;
    public double cinemaPrice;
    public Seat seat[];

    public Cinema(int cinemaID, int totalNumOfSeats, int totalRows) {
        this.cinemaID = cinemaID;
        this.totalNumOfSeats = totalNumOfSeats;
        this.totalRows = totalRows;
        this.seat = new Seat[this.totalNumOfSeats];
    }

    public abstract void setCinemaClassPrice(double defaultPrice, double discount, boolean onDiscount);

    public double getCinemaClassPrice() {
        return this.cinemaPrice;
    }

    public int getCinemaID() {
        return this.cinemaID;
    }

    public int getTotalNumOfSeats() {
        return this.totalNumOfSeats;
    }

    public int getTotalRows() {
        return this.totalRows;
    }

    public void setTotalNumberOfSeats(int totalNumOfSeats) {
        this.totalNumOfSeats = totalNumOfSeats;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public Seat[] getSeats() {
        return this.seat;
    }
}
