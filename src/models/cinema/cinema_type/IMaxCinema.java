package models.cinema.cinema_type;

import models.cinema.Cinema;

public class IMaxCinema extends Cinema{
    public int cinemaID;
    public int totalRows;
    public int totalNumOfSeats;
    public double cinemaPrice;

    /*
     * Constructor
     */
    public IMaxCinema(int cinemaID, int totalRows, int totalNumOfSeats) {
        super(cinemaID, totalNumOfSeats, totalRows);
    }

    /*
     * Concrete implementation of abstract method setCinemaClassPrice for class IMaxCinema
     */
    public void setCinemaClassPrice(double price, double discount, boolean onDiscount) {
        if (onDiscount) {
            this.cinemaPrice = price - discount;
        }   
        else {
            this.cinemaPrice = price;
        }
    }
}
