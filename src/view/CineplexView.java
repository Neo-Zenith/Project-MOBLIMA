package view;

import java.util.ArrayList;

import controller.CineplexManager;
import controller.DatabaseManager;
import model.Cineplex;
import handler.InputHandler;

public class CineplexView extends MainView {
    
    private ArrayList <Cineplex> cineplexes;
    private CinemaView cinemaView;

    public CineplexView() {}
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("You may view all the cineplexes supported by our App here:");
        this.cineplexes = CineplexManager.printCineplexesInfo();
        System.out.println(this.cineplexes.size() + 1 + ". Return back.");
        MainView.printBoilerPlate("""
                Select one of the cineplexes to view further informations.
                """);
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            if (choice > this.cineplexes.size() || choice < 0) {
                break;
            }
            this.cinemaView = new CinemaView(cineplexes.get(choice - 1));
            this.cinemaView.appContent();
            
        }   while (choice <= this.cineplexes.size() && choice > 0);
    }
}
