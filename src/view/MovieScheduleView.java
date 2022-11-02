package view;

import java.util.ArrayList;

import controller.MovieScheduleManager;
import model.Cinema;
import model.Seat;
import model.MovieSchedule;
import handler.InputHandler;

public class MovieScheduleView {
    private ArrayList <MovieSchedule> movieSchedules;
    private Cinema cinema;
    private SeatingPlanView seatingPlanView;

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
            MovieSchedule movieSchedule = this.movieSchedules.get(choice - 1);
            int index = MovieScheduleManager.getShowingVenueByIndex(movieSchedule, cinema);
            ArrayList <Seat> seatingPlan = this.movieSchedules.get(choice - 1).getSeatingPlan().get(index);
            this.seatingPlanView = new SeatingPlanView(movieSchedule, cinema, seatingPlan);
            this.seatingPlanView.appContent();

        }   while (choice <= this.movieSchedules.size() && choice > 0);
    }
}
