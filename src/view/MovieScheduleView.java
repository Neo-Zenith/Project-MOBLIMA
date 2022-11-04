package view;

import model.MovieSchedule;
import controller.MovieScheduleManager;
import handler.InputHandler;
import model.Cinema;
import model.Movie;
import model.MovieGoer;

import java.util.ArrayList;

public class MovieScheduleView {
    private Movie movie;
    private Cinema cinema;
    private MovieSchedule movieSchedule;
    private int num = 0;
    private MovieGoer movieGoer;
    private SeatingPlanView seatingPlanView;

    public MovieScheduleView(Cinema cinema, Movie movie, MovieGoer movieGoer) {
        this.movie = movie;
        this.cinema = cinema;
        this.movieSchedule = MovieScheduleManager.filterMovieSchedulesByMovie(movie);
        this.movieGoer = movieGoer;
    }
    
    public void printMovieSchedule() {
        for (int i = 0; i < this.movieSchedule.getShowingVenues().size(); i ++) {
            Cinema showingVenue = this.movieSchedule.getShowingVenues().get(i);

            if (showingVenue.getUUID().equals(this.cinema.getUUID())) {
                System.out.print((++this.num) + ". ");
                this.movieSchedule.getShowingTime().get(i).printTime();
                System.out.println("");
            }
        }
        System.out.println((++this.num) + ". Return");

    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a movie schedule to view the seating plan: ");
        System.out.println("====================================");
    }

    public void appContent() {
        this.printMovieSchedule();
        int choice = -1;
        do{
            this.printMenu();
            choice = InputHandler.intHandler();
            while(choice <0 || choice>this.num){
                System.out.println("Please enter a valid input");
                choice = InputHandler.intHandler();
            }
            if(choice == this.num){
                return;
            }

            this.seatingPlanView = new SeatingPlanView(this.movieSchedule, this.movieSchedule.getShowingVenues().get(choice-1),
            this.movieSchedule.getShowingVenues().get(choice-1).getSeats(), movieGoer);
            this.seatingPlanView.appContent();
            
            if(MovieMenuView.exit){
                return;
            }
        }while(choice>0 && choice<=this.num);
        
    }
}
