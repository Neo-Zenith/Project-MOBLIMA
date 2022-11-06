package view;

import model.*;
import controller.*;

import java.util.*;

public class MovieTicketView {

    private ArrayList<MovieTicket> movieTicketList;
    private ArrayList<String> seatID; 
    
    public MovieTicketView(ArrayList<String> seatID, Movie movie, DateTime showingTime, Cinema cinema, ArrayList <Seat> seatingPlan, double totalMovieTicketPrice){
        this.seatID = seatID;
        this.movieTicketList = MovieTicketManager.createMovieTicketList(seatID, movie, showingTime, cinema, seatingPlan, totalMovieTicketPrice);
    }

    public ArrayList <MovieTicket> getMovieTickets() {
        return this.movieTicketList;
    }

    public void printMovieTickets(ArrayList<MovieTicket> movieTicketList, double totalMovieTicketPrice){

        // uncomment this after getCineplexByCinema() is created
        Cineplex targetCineplex = CineplexManager.getCineplexByCinema(movieTicketList.get(0).getShowingVenue());

        // we assume that all ticket has the same Cinema, Movie and DateTime, but different Seat (seatID)
        Movie movie = movieTicketList.get(0).getMovieToWatch();
        Cinema cinema = movieTicketList.get(0).getShowingVenue();
        DateTime dateTime = movieTicketList.get(0).getShowTime();

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
        String seatID;
        double pricePerMovieTicket = totalMovieTicketPrice/movieTicketList.size();

        System.out.println("Here is your Movie Ticket(s): ");
        System.out.println("Total number of movie ticket: " + movieTicketList.size());
        System.out.println("[ Please keep it(them) as the proof for entrance ]");
        System.out.println("");
        for(int i=0; i<movieTicketList.size(); i++){
            seatID = movieTicketList.get(i).getBookedSeat().getUUID();

            System.out.println("====================================");
            System.out.println("               MOBLIMA              ");
            System.out.println("                                    ");
            System.out.println("             MOVIE TICKET " + (i+1) );
            System.out.println("___________________________________");
            System.out.println("Movie Name: " + movieName);
            System.out.println("Movie Type: " + movieType);
            System.out.println("Cineplex: " + cineplex);
            System.out.println("Cinema Class: " + cinemaClass);
            System.out.println("Cinema ID: " + cinemaId);         // Cinema uuid -> Cinema Hall Number (ex. Hall 3) 
            System.out.println("Showing Date: " + date + "/" + month + "/" + year);
            System.out.println("Showing Time: " + hour + ":" + minute);
            System.out.println("Seat ID: " + seatID);  // seat uuid -> seat ID
            System.out.println("Price ($)/ ticket: " + pricePerMovieTicket);
            System.out.println("_________________________________-__");
            System.out.println("                                    ");
            System.out.println("          Enjoy Your Movie!         ");
            System.out.println("====================================");
        }
    }
}
