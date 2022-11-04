package controller;

import model.MovieTicket;
import model.Movie;
import model.DateTime;
import model.Seat;
import model.Cinema;
import handler.DatabaseHandler;
import database.Database;

import java.util.*;

public class MovieTicketManager {

    public MovieTicketManager() {}

    public static MovieTicket createMovieTicket(Movie movieToWatch, DateTime showTime, Seat bookedSeat, Cinema cinema){
        String UUID = String.format("MT%04d", DatabaseHandler.generateUUID(Database.MOVIE_TICKET));
        MovieTicket movieTicket = new MovieTicket(UUID, movieToWatch, showTime, bookedSeat, cinema);
        DatabaseManager.saveUpdateToDatabase(UUID, movieTicket, Database.MOVIE_TICKET);
        return movieTicket;
    }


}
