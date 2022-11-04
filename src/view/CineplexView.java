package view;

import java.util.ArrayList;
import model.Movie;
import model.MovieSchedule;
import model.Cineplex;
import controller.CineplexManager;
import controller.MovieManager;
import controller.MovieScheduleManager;
import handler.InputHandler;


public class CineplexView extends MainView {
    private Movie movie;
    private ArrayList <Cineplex> cineplexes;
    private CinemaView cinemaView;

    public CineplexView(Movie movie) {
        this.movie = movie;
        this.cineplexes = CineplexManager.filterCineplexesByMovie(this.movie);
    }


    public void printCineplex() {
        System.out.println("====================================");
        for (int i = 0; i < cineplexes.size(); i ++) {
            Cineplex cineplex = cineplexes.get(i);
            System.out.println((i+1) + ". " + cineplex.getCineplexName());
        }
        System.out.println((cineplexes.size()+1) + ". Return");
    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a cineplex to view the cinemas available: ");
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        this.printCineplex();

        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            while(choice <=0 || choice > this.cineplexes.size()+1){
                System.out.println("Please enter a valid input!");
                choice = InputHandler.intHandler();;
            }
            if (choice == this.cineplexes.size()+1){
                return;
            }
            this.cinemaView = new CinemaView(cineplexes.get(choice-1), this.movie);
            this.cinemaView.appContent();
            
            if(MovieMenuView.exit){
                return;
            }

        }while (choice > 0 && choice <= this.cineplexes.size());
    }
}

