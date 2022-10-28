package models.user;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

import models.movie.*;
import models.cinema.*;

public class Payment {
    private String cinemaCode;
    private String movieTitle;
    private MovieTicket movieTicket;
    private int seatRowID;
    private int seatColumnID;
    private Boolean paymentStatus;
    private double finalPrice;
    private String transactionID;

    public Payment(String cinemaCode, String movieTitle, int seatRowID, int seatColumnID, double finalPrice) {
        this.cinemaCode = cinemaCode;
        this.movieTitle = movieTitle;
        this.seatRowID = seatRowID;
        this.seatColumnID = seatColumnID;
        this.paymentStatus = false;
        this.finalPrice = finalPrice;
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

    public Boolean getPaymentStatus(){
        return this.paymentStatus;
    }

    public double getFinalPrice(){
        return this.finalPrice;
    }

    public String getCinemaCode(){
        return this.cinemaCode;
    }

    public void setPaymentStatus(Boolean paymentStatus){
        this.paymentStatus = paymentStatus;
    }

    public String generateTransactionId(){
        String cinemaCode = getCinemaCode();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");  
        LocalDateTime now = LocalDateTime.now(); 
        this.transactionID = cinemaCode + dtf.format(now);
        return this.transactionID;
    }
}
