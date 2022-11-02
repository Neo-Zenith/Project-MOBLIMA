package model;

import java.util.ArrayList;

import model.enums.CinemaClass;

public class StandardCinema extends Cinema{
    private double cinemaPrice;
    
    public StandardCinema(  String UUID, CinemaClass cinemaClass, 
                            ArrayList <Seat> seats, double cinemaPrice,
                            int numOfRows, int totalNumOfSeats) {
        super(UUID, cinemaClass, seats, cinemaPrice, numOfRows, totalNumOfSeats);
    }

    public void setCinemaPrice(double cinemaPrice) {
        this.cinemaPrice = cinemaPrice;
    }
}
