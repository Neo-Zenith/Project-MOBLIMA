package controller;

import model.*;
import handler.*;
import database.*;
import java.util.*;

public class MovieTicketManager {

    public MovieTicketManager() {}

    public static MovieTicket createMovieTicket(Movie movieToWatch, DateTime showTime, Seat bookedSeat, Cinema cinema){
        String UUID = String.format("MT%04d", DatabaseHandler.generateUUID(Database.MOVIE_TICKET));
        MovieTicket movieTicket = new MovieTicket(UUID, movieToWatch, showTime, bookedSeat, cinema);
        DatabaseManager.saveUpdateToDatabase(UUID, movieTicket, Database.MOVIE_TICKET);
        return movieTicket;
    }

    public static ArrayList<MovieTicket> createMovieTicketList(ArrayList<String> seatID, Movie movieToWatch, DateTime showTime, Cinema cinema, ArrayList <Seat> seatingPlan, double totalMovieTicketPrice){
        // create a new list of movie tickets based on the number of seatID got from user
        ArrayList <MovieTicket> movieTickets = new ArrayList<MovieTicket>();

        for(int i = 0; i < seatID.size(); i ++) { 
            Seat seat = SeatManager.getSeatBySeatID(seatID.get(i), seatingPlan, cinema);
            MovieTicket newMovieTicket = MovieTicketManager.createMovieTicket(movieToWatch, showTime, seat, cinema);
            movieTickets.add(newMovieTicket);
        }

        return movieTickets;
    }

}
