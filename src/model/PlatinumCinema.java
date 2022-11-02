package model;

import java.util.ArrayList;

import model.enums.CinemaClass;

public class PlatinumCinema extends Cinema{
    private double cinemaPrice;
    
    public PlatinumCinema(  String UUID, CinemaClass cinemaClass, 
                            ArrayList <Seat> seats, double cinemaPrice,
                            int numOfRows, int totalNumOfSeats) {
        super(UUID, cinemaClass, seats, cinemaPrice, numOfRows, totalNumOfSeats);
    }

    public void setCinemaPrice(double cinemaPrice) {
        this.cinemaPrice = cinemaPrice;
    }
}
