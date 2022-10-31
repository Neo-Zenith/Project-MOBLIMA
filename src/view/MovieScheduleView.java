package view;

import java.util.ArrayList;

import controller.MovieScheduleManager;
import model.Cinema;
import model.MovieSchedule;
import handler.InputHandler;

public class MovieScheduleView {
    private ArrayList <MovieSchedule> movieSchedules;
    private Cinema cinema;

    public MovieScheduleView(Cinema cinema) {
        this.cinema = cinema;
    }
    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Viewing all schedules under " + this.cinema.getUUID());
        this.movieSchedules = MovieScheduleManager.printMovieSchedule(this.cinema);
        System.out.println(this.movieSchedules.size() + 1 + ". Return back.");
        MainView.printBoilerPlate("""
                Select one of the schedules to enquire about booking matters.
                """);
        System.out.println("====================================");
    }

    public void appContent() {
        int choice = -1;
        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            if (choice > this.movieSchedules.size() || choice < 0) {
                break;
            }
            
        }   while (choice <= this.movieSchedules.size() && choice > 0);
    }
}
