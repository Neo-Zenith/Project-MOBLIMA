package view;

import model.Cinema;
import model.Cineplex;
import model.enums.CinemaClass;
import model.*;

import java.util.ArrayList;

import controller.CinemaManager;
import handler.InputHandler;

public class CinemaView extends MainView {
    private Movie movie;
    private Cineplex cineplex;
    private ArrayList<Cinema> listOfCinemaClass;
    private MovieScheduleView movieScheduleView;
    private MovieGoer movieGoer;

    public CinemaView(Cineplex cineplex, Movie movie, MovieGoer movieGoer) {
        this.movie = movie;
        this.cineplex = cineplex;
        this.listOfCinemaClass = CinemaManager.filterCinemaByCineplexMovie(cineplex, movie);
        this.movieGoer = movieGoer;
    }

    public void printCinemas() {
        ArrayList<CinemaClass> existingClass = new ArrayList<CinemaClass>();
        System.out.println("====================================");
        for (int i = 0; i < this.listOfCinemaClass.size(); i++) {
            Cinema cinema = this.listOfCinemaClass.get(i);
            if (!existingClass.contains(cinema.getCinemaClass())) {
                existingClass.add(cinema.getCinemaClass());
                System.out.println((i + 1) + ". " + cinema.getCinemaClass());
            }
        }
        System.out.println((this.listOfCinemaClass.size() + 1) + ". Return");
    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a cinema to view the movie schedule: ");
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        this.printCinemas();
        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            while (choice < 0 || choice > this.listOfCinemaClass.size() + 1) {
                System.out.println("Please enter a valid input");
                choice = InputHandler.intHandler();
            }
            if (choice == this.listOfCinemaClass.size() + 1) {
                return;
            }

            this.movieScheduleView = new MovieScheduleView(listOfCinemaClass, this.movie, this.movieGoer);
            this.movieScheduleView.appContent();

            if (MovieMenuView.exit) {
                return;
            }
        } while (choice > 0 && choice <= this.listOfCinemaClass.size());

    }
}
