package view;

import java.util.ArrayList;

import controller.SeatManager;
import model.Cinema;
import model.MovieSchedule;
import model.Seat;
import handler.InputHandler;

public class SeatingPlanView {
    private ArrayList <Seat> seatingPlan;
    private Cinema cinema;
    private MovieSchedule movieSchedule;

    public SeatingPlanView(MovieSchedule movieSchedule, Cinema cinema, ArrayList <Seat> seatingPlan) {
        this.seatingPlan = seatingPlan;
        this.cinema = cinema;
        this.movieSchedule = movieSchedule;
    }
    
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                Select one of the options below:
                1. Book Seat.
                2. Return back.
                """);
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        do {
            System.out.println("Cinema ID: " + this.cinema.getUUID());
            System.out.println("Movie Showing: " + this.movieSchedule.getMovieOnShow().getMovieTitle());
            System.out.print("Showing Time: ");
            this.movieSchedule.getShowingTime().printTime();
            System.out.println("");

            SeatManager.printCinemaFloorMap(seatingPlan);
            this.printMenu();
            choice = InputHandler.intHandler();

            switch (choice) {
                case 1:
                    System.out.println("Enter the seatID to be booked: ");
                    String seatID = InputHandler.StringHandler();
                    if (SeatManager.bookSeat(seatID, movieSchedule, cinema)) {
                        System.out.println("Booking has been made!");
                    }
                    break;
                case 2:
                    break;
            }
            
        }   while (choice != 2);
    }
}

