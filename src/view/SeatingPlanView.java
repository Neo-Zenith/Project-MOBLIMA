package view;

import java.util.ArrayList;

import controller.MovieScheduleManager;
import controller.SeatManager;
import model.BookingHistory;
import model.Cinema;
import model.MovieGoer;
import model.MovieSchedule;
import model.Seat;
import model.enums.CinemaClass;
import handler.InputHandler;
import controller.PaymentManager;
import view.PaymentView;
import model.Payment;
import model.MovieTicket;
import model.Movie;
import model.BookingHistory;
import controller.BookingHistoryManager;

public class SeatingPlanView {
    private ArrayList <Seat> seatingPlan;
    private Cinema cinema;
    private MovieSchedule movieSchedule;
    private MovieGoer movieGoer;
    private PaymentView paymentView;
    private Payment paymentCreated;
    private MovieTicketView movieTicketView;
    private ArrayList<MovieTicket> movieTicketListCreated;
    private Seat seatBooked;
    private BookingHistory bookingHistoryCreated;

    public SeatingPlanView(MovieSchedule movieSchedule, Cinema cinema, ArrayList <Seat> seatingPlan, MovieGoer movieGoer) {
        this.seatingPlan = seatingPlan;
        this.cinema = cinema;
        this.movieSchedule = movieSchedule;
        this.movieGoer = movieGoer;
    }
    
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                Select one of the options below:
                1. Add Seat into Booking Cart.
                2. Check Out and Proceed To Payment.
                3. Return back.
                """);
        System.out.println("====================================");
    }

    public void appContent() {

        int choice = -1;
        ArrayList<String> seatIDList = new ArrayList<String>();
        double currentMovieTicketPrice = 0;
        double totalMovieTicketPrice = 0;
        
        do {
            System.out.println("Cinema ID: " + this.cinema.getUUID());
            System.out.println("Movie Showing: " + this.movieSchedule.getMovieOnShow().getMovieTitle());
            System.out.print("Showing Time: ");
            int index = MovieScheduleManager.getShowingVenueIndex(movieSchedule, cinema);
            this.movieSchedule.getShowingTime().get(index).printTime();
            System.out.println("");

            if (cinema.getCinemaClass() == CinemaClass.PLATINUM) {
                SeatManager.printPlatinumCinemaFloorMap(seatingPlan);
            }
            else {
                SeatManager.printStandardCinemaFloorMap(seatingPlan);
            }
            
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    System.out.println("Enter the seatID to be booked: ");
                    String seatID = InputHandler.stringHandler();
                    if (SeatManager.bookSeat(seatID, movieSchedule, cinema)) {
                        // add seatID into seatIDList => add new ticket into bucket list
                        seatIDList.add(seatID);

                        int seatIDIndex = SeatManager.seatIDConverter(seatID, cinema);
                        this.seatBooked = seatingPlan.get(seatIDIndex);
                        currentMovieTicketPrice = PaymentManager.calculateMovieTicketPrice(this.cinema, this.movieSchedule, this.movieGoer, seatBooked);
                        totalMovieTicketPrice += currentMovieTicketPrice;

                        System.out.println("This Booking has been added!");
                    }
                    break;

                case 2:
                    // create new payment + new movie ticket list => then put into new booking history
                    
                    //cinema code is the last 3 characters in cinema UUID
                    String cinemaUUID = this.cinema.getUUID(); 
                    int length = cinemaUUID.length();
                    String cinemaCode = cinemaUUID.substring(length - 3);
                    this.paymentView = new PaymentView();
                    this.paymentCreated = this.paymentView.createPayment(cinemaCode, totalMovieTicketPrice);
                    this.movieTicketView = new MovieTicketView();
                    Movie movie = this.movieSchedule.getMovieOnShow();
                    this.movieTicketListCreated = this.movieTicketView.createMovieTicketList(seatIDList, movie, this.movieSchedule.getShowingTime().get(index), this.seatBooked, this.cinema, totalMovieTicketPrice);
                    this.bookingHistoryCreated = BookingHistoryManager.createBookingHistory(this.movieTicketListCreated, this.paymentCreated);

                    System.out.println("Your Booking History has been saved!");
                    break;

                case 3:
                    // return
                    break;
                    
            }
            
        }   while (choice == 1);
    }
}

