package view;

import java.util.ArrayList;

import controller.CinemaManager;
import controller.MovieScheduleManager;
import model.Cinema;
import model.Cineplex;
import handler.InputHandler;

public class CinemaView {
    
    private ArrayList <Cinema> cinemas;
    private Cineplex cineplex;
    private MovieScheduleView movieScheduleView;

    public CinemaView(Cineplex cineplex) {
        this.cineplex = cineplex;
    }
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Viewing all cinemas under " + this.cineplex.getCineplexName());
        this.cinemas = CinemaManager.printCinemasInfo(this.cineplex);
        System.out.println(this.cinemas.size() + 1 + ". Return back.");
        MainView.printBoilerPlate("""
                Select one of the cinemas to view further informations.
                """);
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            if (choice > this.cinemas.size() || choice < 0) {
                break;
            }
            this.movieScheduleView = new MovieScheduleView(this.cinemas.get(choice - 1));
            this.movieScheduleView.appContent();
            
        }   while (choice <= this.cinemas.size() && choice > 0);
    }
}
