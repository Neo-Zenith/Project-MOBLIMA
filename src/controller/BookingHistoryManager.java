package controller;

import java.util.*;

import database.Database;
import handler.DatabaseHandler;
import model.Movie;
import model.BookingHistory;
import model.MovieTicket;
import model.MovieGoer;
import model.Payment;
import model.MovieGoer;

public class BookingHistoryManager {

    public BookingHistoryManager() {
    }

    public static BookingHistory createBookingHistory(ArrayList<MovieTicket> movieTicket, Payment payment,
            MovieGoer goer) {
        String UUID = String.format("BH%04d", DatabaseHandler.generateUUID(Database.BOOKING_HISTORY));
        BookingHistory bookingHistory = new BookingHistory(UUID, movieTicket, payment);
        goer.addBookingHistory(bookingHistory);
        DatabaseManager.saveUpdateToDatabase(UUID, bookingHistory, Database.BOOKING_HISTORY);
        DatabaseManager.saveUpdateToDatabase(goer.getUUID(), goer, Database.MOVIE_GOER);
        System.out.println(goer.getBookingHistory().get(0).getMovieTicket().get(0).getMovieToWatch().getMovieTitle());
        return bookingHistory;
    }

    public ArrayList<BookingHistory> getGoerBookingHistories(MovieGoer movieGoer) {
        return movieGoer.getBookingHistory();
    }

    public ArrayList<BookingHistory> queryGoerBookingHistoriesByMovie(MovieGoer movieGoer, Movie movie) {
        ArrayList<BookingHistory> filteredBookingHistories = new ArrayList<>();

        for (int i = 0; i < movieGoer.getBookingHistory().size(); i++) {
            BookingHistory bookingHistory = movieGoer.getBookingHistory().get(i);
            for (int j = 0; j < bookingHistory.getMovieTicket().size(); j++) {
                if (bookingHistory.getMovieTicket().get(j).getMovieToWatch() == movie) {
                    filteredBookingHistories.add(bookingHistory);
                    break;
                }
            }
        }

        return filteredBookingHistories;
    }
}
