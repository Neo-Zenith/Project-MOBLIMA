package view;

import model.Cinema;
import model.Cineplex;
import model.Movie;

import java.util.ArrayList;

import controller.CinemaManager;

public class CinemaView extends MainView{
    private Movie movie;
    private Cineplex cineplex;
    private ArrayList <Cinema> listOfCinemas;

    public CinemaView(Cineplex cineplex, Movie movie) {
        this.movie = movie;
        this.cineplex = cineplex;
        this.listOfCinemas = CinemaManager.filterCinemaByCineplexMovie(cineplex, movie);
    }
    
    public void printCinemas() {
        System.out.println("====================================");

        int index = 1;
        for (int i = 0; i < this.listOfCinemas.size(); i ++) {
            Cinema cinema = this.listOfCinemas.get(i);
            System.out.println(index + ". " + cinema.getCinemaClass());
        }
    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a cinema to view the movie schedule: ");
        System.out.println("====================================");
    }

    public void appContent() {
        this.printMenu();
    }
}
