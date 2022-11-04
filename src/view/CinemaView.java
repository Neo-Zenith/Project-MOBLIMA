package view;

import model.Cinema;
import model.Cineplex;
import model.*;

import java.util.ArrayList;

import controller.CinemaManager;
import handler.InputHandler;

public class CinemaView extends MainView{
    private Movie movie;
    private Cineplex cineplex;
    private ArrayList <Cinema> listOfCinemas;
    private MovieScheduleView movieScheduleView;
    private MovieGoer movieGoer;

    public CinemaView(Cineplex cineplex, Movie movie, MovieGoer movieGoer) {
        this.movie = movie;
        this.cineplex = cineplex;
        this.listOfCinemas = CinemaManager.filterCinemaByCineplexMovie(cineplex, movie);
        this.movieGoer = movieGoer;
    }
    
    public void printCinemas() {
        System.out.println("====================================");
        for (int i = 0; i < this.listOfCinemas.size(); i ++) {
            Cinema cinema = this.listOfCinemas.get(i);
            System.out.println((i+1) + ". " + cinema.getCinemaClass());
        }
        System.out.println((this.listOfCinemas.size()+1) + ". Return");
    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a cinema to view the movie schedule: ");
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        this.printCinemas();
        do{
            this.printMenu();
            choice = InputHandler.intHandler();
            while(choice<0 || choice >this.listOfCinemas.size()+1){
                System.out.println("Please enter a valid input");
                choice = InputHandler.intHandler();
            }
            if (choice == this.listOfCinemas.size()+1){
                return;
            }

            this.movieScheduleView = new MovieScheduleView(listOfCinemas.get(choice-1), this.movie, this.movieGoer);
            this.movieScheduleView.appContent();

            if(MovieMenuView.exit){
                return;
            }
        }while(choice>0 && choice<=this.listOfCinemas.size());
        
    }
}
