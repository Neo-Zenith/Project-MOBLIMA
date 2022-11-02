package controller;

import java.util.ArrayList;

import database.Database;
import handler.DatabaseHandler;
import model.Movie;
import model.BookingHistory;
import model.MovieTicket;
import model.Payment;

public class BookingHistoryManager {
    
    public BookingHistoryManager() {}

    public BookingHistory createBookingHistory(MovieTicket movieTicket, Payment payment) {
        String UUID = String.format("BH%04d", DatabaseHandler.generateUUID(Database.BOOKING_HISTORY));
        BookingHistory bookingHistory = new BookingHistory(UUID, movieTicket, payment);
        DatabaseManager.saveUpdateToDatabase(UUID, bookingHistory, Database.BOOKING_HISTORY);
        return bookingHistory;
    }

    public ArrayList <BookingHistory> getGoerBookingHistories(MovieGoer movieGoer) {
        return movieGoer.getBookingHistory();
    }

    public ArrayList <BookingHistory> queryGoerBookingHistoriesByMovie(MovieGoer movieGoer, Movie movie) {
        ArrayList <BookingHistory> filteredBookingHistories = new ArrayList<>();

        for (int i = 0; i < movieGoer.getBookingHistory(); i ++) {
            BookingHistory bookingHistory = movieGoer.getBookingHistory().get(i);
            if (bookingHistory.getMovieTicket().getMovieToWatch().contains(movie)) {
                filteredBookingHistories.add(bookingHistory);
            }
        }

        return filteredBookingHistories;
    }
}
