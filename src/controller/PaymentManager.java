package controller;

import database.Database;
import handler.DatabaseHandler;
import model.BankTransactionPayment;
import model.Cinema;
import model.MovieSchedule;
import model.Seat;
import model.CardPayment;
import model.Payment;
import model.QRCodePayment;
import model.enums.PaymentType;

public class PaymentManager {
    
    public PaymentManager() {}

    public static Payment createQRCodePayment(String transactionID, double movieTicketPrice) {
        String UUID = String.format("QR%04d", DatabaseHandler.generateUUID(Database.PAYMENT));
        Payment payment = new QRCodePayment(UUID, PaymentType.QRCODE, transactionID, movieTicketPrice);
        DatabaseManager.saveUpdateToDatabase(UUID, payment, Database.PAYMENT);
        return payment;
    }

    public static Payment createBankTransactioPayment(String transactionID, double movieTicketPrice) {
        String UUID = String.format("BT%04d", DatabaseHandler.generateUUID(Database.PAYMENT));
        Payment payment = new BankTransactionPayment(UUID, PaymentType.BANK_TRANSACTION, transactionID, movieTicketPrice);
        DatabaseManager.saveUpdateToDatabase(UUID, payment, Database.PAYMENT);
        return payment;
    }

    public static Payment createCardPayment(String transactionID, double movieTicketPrice) {
        String UUID = String.format("CD%04d", DatabaseHandler.generateUUID(Database.PAYMENT));
        Payment payment = new CardPayment(UUID, PaymentType.CARD_PAYMENT, transactionID, movieTicketPrice);
        DatabaseManager.saveUpdateToDatabase(UUID, payment, Database.PAYMENT);
        return payment;
    }

    public static double calculateMovieTicketPrice(Cinema cinema, MovieSchedule movieSchedule, MovieGoer movieGoer, Seat seat) {
        return cinema.getCinemaPrice() + movieSchedule.getMovieOnShow().getMoviePrice() + seat.getSeatPrice() + movieGoer.getGoerPrice();
    }
}
