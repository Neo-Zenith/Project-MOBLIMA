package view;

import middleware.*;

public class MovieAppView extends MainView{
    
    private DatabaseView dView;

    public MovieAppView() {
        super();
        this.dView = new DatabaseView();
    }

    public void printMenuScreen() {
        System.out.println("======================");
        System.out.println("Welcome to MOBILIMA!");
        System.out.println("What would you like to do?");
        System.out.println("1. Manage Database");
        System.out.println("======================");
    }

    public void appView() {
        int choice;

        do {
            printMenuScreen();
            choice = InputHandler.readInt();

            switch (choice) {
                case 1:
                    UIHandler.clearScreen();
                    dView.appView();
                    UIHandler.clearScreen();
                    break;
                
                default:
                    break;
            }
        }   while (choice != 4);
    }
}
