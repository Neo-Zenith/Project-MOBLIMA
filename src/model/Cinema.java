package model;

import java.util.ArrayList;

import model.enums.CinemaClass;

public abstract class Cinema {
    
    private String UUID;
    private CinemaClass cinemaClass;
    private ArrayList <Seat> seats;
    private double cinemaPrice;
    private int numOfRows;
    private int totalNumOfSeats;

    public Cinema(  String UUID, CinemaClass cinemaClass, 
                    ArrayList <Seat> seats, double cinemaPrice,
                    int numOfRows, int totalNumOfSeats) {
        this.setUUID(UUID);
        this.setCinemaClass(cinemaClass);
        this.setSeats(seats);
        this.setCinemaPrice(cinemaPrice);
        this.setNumOfRows(numOfRows);
        this.setTotalNumOfSeats(totalNumOfSeats);
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public CinemaClass getCinemaClass() {
        return this.cinemaClass;
    }

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public ArrayList <Seat> getSeats() {
        return this.seats;
    }

    public void setSeats(ArrayList <Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
        this.totalNumOfSeats ++;
    }

    public double getCinemaPrice() {
        return this.cinemaPrice;
    }

    public abstract void setCinemaPrice(double cinemaPrice);

    public int getNumOfRows() {
        return this.numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getTotalNumOfSeats() {
        return this.totalNumOfSeats;
    }

    public void setTotalNumOfSeats(int totalNumOfSeats) {
        this.totalNumOfSeats = totalNumOfSeats;
    }
}
