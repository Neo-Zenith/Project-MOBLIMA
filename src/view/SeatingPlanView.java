package view;

import java.util.*;

import controller.*;
import database.Database;
import model.*;
import model.enums.*;
import handler.*;
import view.*;

public class SeatingPlanView {
    private ArrayList<Seat> seatingPlan;
    private DateTime showingTime;
    private Cinema cinema;
    private MovieSchedule movieSchedule;
    private MovieGoer movieGoer;
    private PaymentView paymentView;
    private Payment paymentCreated;
    private MovieTicketView movieTicketView;
    private ArrayList<MovieTicket> movieTicketListCreated;
    private Seat seatBooked;
    private BookingHistory bookingHistoryCreated;
    private String errorMessage;
    private ArrayList<String> seatIDList;
    private double currentMovieTicketPrice;
    private double totalMovieTicketPrice;

    public SeatingPlanView(MovieSchedule movieSchedule, Cinema cinema, ArrayList<Seat> seatingPlan,
            MovieGoer movieGoer) {
        this.seatingPlan = seatingPlan;
        this.cinema = cinema;
        this.movieSchedule = movieSchedule;
        this.movieGoer = movieGoer;
        this.errorMessage = "";
        int index = MovieScheduleManager.getShowingVenueIndex(movieSchedule, cinema);
        this.showingTime = this.movieSchedule.getShowingTime().get(index);
        this.seatIDList = new ArrayList<>();
        this.currentMovieTicketPrice = 0;
        this.totalMovieTicketPrice = 0;
    }

    public void printSeatingPlan() {
        if (this.cinema.getCinemaClass() == CinemaClass.PLATINUM) {
            SeatManager.printPlatinumCinemaFloorMap(this.seatingPlan);
        } else {
            SeatManager.printStandardCinemaFloorMap(this.seatingPlan);
        }
    }

    public void printMenu() {
        MainView.printBoilerPlate("Seat Booking");
        System.out.println("Cinema ID: " + this.cinema.getUUID());
        System.out.println("Movie Showing: " + this.movieSchedule.getMovieOnShow().getMovieTitle());
        System.out.println("Showing Time: " + this.showingTime.getTimeNow());
        this.printSeatingPlan();
        this.printSeatInCart();
        MainView.printMenuContent("""
                01. Add Seat into Booking Cart.
                02. Check Out and Proceed To Payment.
                03. Return back.
                """);
    }

    public void printSeatInCart() {
        String content = "\nSeat in cart: \n";

        for (int i = 0; i < this.seatIDList.size(); i++) {
            String seatID = this.seatIDList.get(i);
            Seat seat = SeatManager.getSeatBySeatID(seatID, this.seatingPlan, this.cinema);
            String index = String.format("%02d. ", i + 1);
            String payload = String.format(index + seatID + "\t" + seat.getSeatType().getDisplayName() + "\n");
            content = content + payload;
        }

        String payload = String.format("Total price: \t%.2f", this.totalMovieTicketPrice);
        content = content + payload;
        MainView.printMenuContent(content);
    }

    public void appContent() {
        int choice = -1;

        do {
            if (MovieMenuView.exit) {
                this.errorMessage = "";
                return;
            }
            
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();
            if (choice < 0 || choice > 3) {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            switch (choice) {
                case 1:
                    UIHandler.clearScreen();
                    System.out.println("Enter the seat ID to be booked: ");
                    String seatID = InputHandler.stringHandler();
                    int index = SeatManager.seatIDConverter(seatID, this.cinema);
                    if (index == -1) {
                        this.errorMessage = "Error! Please enter a valid seat ID!";
                        continue;
                    }
                    this.seatBooked = this.seatingPlan.get(index);

                    if (SeatManager.bookSeat(seatID, this.movieSchedule, this.cinema)) {
                        // add seatID into seatIDList => add new ticket into bucket list
                        seatIDList.add(seatID);
                        if (this.seatBooked.getSeatType() == SeatType.STANDARD) {
                            this.currentMovieTicketPrice = PaymentManager.calculateMovieTicketPrice(this.cinema,
                                    this.movieSchedule, this.movieGoer, this.seatBooked);
                        } else {
                            this.currentMovieTicketPrice = PaymentManager.calculateMovieTicketPrice(this.cinema,
                                    this.movieSchedule, this.movieGoer, this.seatBooked) * 2;
                        }

                        this.totalMovieTicketPrice += this.currentMovieTicketPrice;
                        this.errorMessage = "Booking has been made!";
                    } else {
                        this.errorMessage = "Error! Booking cannot be made on the seat selected!";
                    }
                    break;

                case 2:
                    // create new payment + new movie ticket list => then put into new booking
                    // history

                    // cinema code is the last 3 characters in cinema UUID
                    String cinemaCode = CinemaManager.getCinemaCode(this.cinema);
                    this.paymentView = new PaymentView(cinemaCode, this.totalMovieTicketPrice, this.movieSchedule);
                    this.errorMessage = "";
                    this.paymentView.appContent();
                    if (MovieMenuView.exit) {
                        this.paymentCreated = this.paymentView.getPayment();
                        Movie movie = this.movieSchedule.getMovieOnShow();
                        this.movieTicketView = new MovieTicketView(this.seatIDList, movie, this.showingTime,
                                this.cinema,
                                this.seatingPlan, this.totalMovieTicketPrice);
                        movieTicketView.printMovieTickets();
                        MovieManager.updateMovieTicketSold(movie, seatIDList.size());
                        this.movieTicketListCreated = this.movieTicketView.getMovieTickets();
                        this.bookingHistoryCreated = BookingHistoryManager.createBookingHistory(
                                this.movieTicketListCreated,
                                this.paymentCreated, this.movieGoer);
                        System.out.println("Press any key to return");
                        InputHandler.stringHandler();
                    }
                    break;

                case 3:
                    this.errorMessage = "";
                    return;

            }
            if (MovieMenuView.exit) {
                return;
            }
        } while (true);
    }
}
