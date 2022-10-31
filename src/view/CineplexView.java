package view;

import handler.InputHandler;

import java.util.ArrayList;

import controller.CineplexManager;
import controller.DatabaseManager;
import model.Cineplex;

public class CineplexView extends MainView {
    
    private ArrayList <Cineplex> cineplexes;

    public CineplexView() {}
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("You may view all the cineplexes supported by our App here:");
        this.cineplexes = CineplexManager.printCineplexesInfo();
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

            switch (choice) {
                case 1:
                    DatabaseManager.initializeCineplexData();
                    break;
                case 2:
                    DatabaseManager.resetDatabase();
                    break;
                case 3:
                    break;
            }  
        }   while (choice != 3);
    }
}
