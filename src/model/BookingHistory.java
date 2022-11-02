package model;

import java.util.*;

public class BookingHistory {
    private String UUID;
    private ArrayList<MovieTicket> movieTicket;
    private Payment payment;

    public BookingHistory(String UUID, ArrayList<MovieTicket> movieTicket, Payment payment) {
        this.setMovieTicket(movieTicket);
        this.setPayment(payment);
        this.setUUID(UUID);
    }

    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public ArrayList<MovieTicket> getMovieTicket() {
        return this.movieTicket;
    }

    public void setMovieTicket(ArrayList<MovieTicket> movieTicket) {
        this.movieTicket = movieTicket;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
