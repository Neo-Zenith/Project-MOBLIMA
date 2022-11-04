package view;

import java.util.ArrayList;
import model.Movie;
import model.MovieSchedule;
import model.Cineplex;
import controller.CineplexManager;
import controller.MovieManager;
import controller.MovieScheduleManager;

public class CineplexView extends MainView {
    private Movie movie;
    private ArrayList <Cineplex> cineplexes;


    public CineplexView(Movie movie) {
        this.movie = movie;
        this.cineplexes = CineplexManager.filterCineplexesByMovie(this.movie);
    }


    public void printCineplex() {
        System.out.println("====================================");
        int index = 1;
        for (int i = 0; i < cineplexes.size(); i ++) {
            Cineplex cineplex = cineplexes.get(i);
            System.out.println(index + ". " + cineplex.getCineplexName());
        }
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
            
        }   while (choice > 0 && choice <= this.cineplexes.size());
    }
}

