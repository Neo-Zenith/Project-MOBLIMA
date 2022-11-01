package view;

import controller.MovieGoerManager;
import model.MovieGoer;

public class MovieGoerView {

    public void printDetails(String name) {
        MovieGoerManager manager = new MovieGoerManager();
        MovieGoer goer = manager.getGoerDetails(name);
        System.out.println("Name: " + goer.getName());
        System.out.println("Email: " + goer.getEmail());
        System.out.println("Mobile Number: " + goer.getMobileNum());
    }

}
