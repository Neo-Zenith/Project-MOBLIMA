package view;

import model.*;
import controller.*;
import handler.InputHandler;
import handler.UIHandler;

import java.util.*;

public class MovieTicketView {

    private ArrayList<MovieTicket> movieTicketList;
    private ArrayList<String> seatID;
    private double totalMovieTicketPrice;
    private Movie movie;

    public MovieTicketView(ArrayList<String> seatID, Movie movie, DateTime showingTime, Cinema cinema,
            ArrayList<Seat> seatingPlan, double totalMovieTicketPrice) {
        this.seatID = seatID;
        this.movie = movie;
        this.totalMovieTicketPrice = totalMovieTicketPrice;
        this.movieTicketList = MovieTicketManager.createMovieTicketList(seatID, movie, showingTime, cinema, seatingPlan,
                totalMovieTicketPrice);
    }

    public ArrayList<MovieTicket> getMovieTickets() {
        return this.movieTicketList;
    }

    public void printMovieTickets() {

        // uncomment this after getCineplexByCinema() is created
        Cineplex targetCineplex = CineplexManager.getCineplexByCinema(this.movieTicketList.get(0).getShowingVenue());

        // we assume that all ticket has the same Cinema, Movie and DateTime, but
        // different Seat (seatID)
        Movie movie = this.movieTicketList.get(0).getMovieToWatch();
        Cinema cinema = this.movieTicketList.get(0).getShowingVenue();
        DateTime dateTime = this.movieTicketList.get(0).getShowTime();

        String movieName = movie.getMovieTitle();
        String movieType = movie.getMovieType().toString();
        String cineplex = targetCineplex.getCineplexName();
        String cinemaClass = cinema.getCinemaClass().toString();
        String cinemaId = cinema.getUUID();
        int date = dateTime.getDate();
        int month = dateTime.getMonth();
        int year = dateTime.getYear();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        double pricePerMovieTicket = totalMovieTicketPrice / this.movieTicketList.size();

        UIHandler.clearScreen();
        MainView.printBoilerPlate("Movie Tickets");
        System.out.println("Total number of movie ticket: " + this.movieTicketList.size());
        System.out.println("[ Please keep it(them) as the proof for entrance ]");
        System.out.println("");
        for (int i = 0; i < this.movieTicketList.size(); i++) {

            System.out.println("══════════════════════════════════════");
            System.out.println("                MOBLIMA               ");
            System.out.println("                                      ");
            System.out.println("             MOVIE TICKET " + (i + 1));
            System.out.println("___________________________________");
            System.out.println("Movie Name: " + movieName);
            System.out.println("Movie Type: " + movieType);
            System.out.println("Cineplex: " + cineplex);
            System.out.println("Cinema Class: " + cinemaClass);
            System.out.println("Cinema ID: " + cinemaId); // Cinema uuid -> Cinema Hall Number (ex. Hall 3)
            System.out.println("Showing Date: " + date + "/" + month + "/" + year);
            System.out.println("Showing Time: " + hour + ":" + minute);
            System.out.println("Seat ID: " + this.seatID.get(i)); // seat uuid -> seat ID
            System.out.println("Price ($)/ ticket: " + pricePerMovieTicket);
            System.out.println("____________________________________");
            System.out.println("                                    ");
            System.out.println("          Enjoy Your Movie!         ");
            System.out.println("═════════════════════════════════════");
            System.out.println("");
        }
    }
}
