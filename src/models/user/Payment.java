package models.user;

import models.movie.*;

public class Payment {
    private String transactionID;
    private String movieTitle;
    private MovieTicket movieTicket;
    private int seatRowID;
    private int seatColumnID;

    public Payment(String transactionID, String movieTitle, int seatRowID, int seatColumnID) {
        this.transactionID = transactionID;
        this.movieTitle = movieTitle;
        this.seatRowID = seatRowID;
        this.seatColumnID = seatColumnID;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public int getSeatRowID() {
        return this.seatRowID;
    }

    public int getSeatColumnID() {
        return this.seatColumnID;
    }

    public MovieTicket getMovieTicket() {
        return this.movieTicket;
    }
}
