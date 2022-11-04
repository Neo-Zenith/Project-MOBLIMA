package view;

import model.Cineplex;
import model.MovieTicket;
import controller.MovieTicketManager;
import controller.CineplexManager;
import model.Cineplex;
import model.Movie;
import model.enums.MovieType;
import model.Cinema;
import model.DateTime;
import model.Seat;
import model.enums.CinemaClass;

import java.util.*;

public class MovieTicketView {

    private ArrayList<MovieTicket> movieTicketList;
    //private ArrayList<Integer> seatID; 
    
    public MovieTicketView(){
        //this.movieTicketList = movieTicketList;
    }

    public ArrayList<MovieTicket> getMovieTicketList(){
        return this.movieTicketList;
    }

    public void setMovieTicketList(ArrayList<MovieTicket> movieTicketList){
        this.movieTicketList = movieTicketList;
    }

    public ArrayList<MovieTicket> createMovieTicketList(ArrayList<String> seatID, Movie movieToWatch, DateTime showTime, Seat bookedSeat, Cinema cinema, double totalMovieTicketPrice){
        // create a new list of movie tickets based on the number of seatID got from user
        this.movieTicketList = new ArrayList<MovieTicket>();
        for(int i=0; i<seatID.size(); i++){
            MovieTicket newMovieTicket = MovieTicketManager.createMovieTicket(movieToWatch, showTime, bookedSeat, cinema);
            this.movieTicketList.add(newMovieTicket);
        }

        printMovieTickets(this.movieTicketList, totalMovieTicketPrice);

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
