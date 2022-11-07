package view;


import handler.*;
import java.util.ArrayList;
import controller.*;
import model.*;

public class StaffMovieDetailsView {
    private StaffConfigureMovieView staffConfigureMovieView;
    private ArrayList <Movie> allMovies;
    private String errorMessage;

    public StaffMovieDetailsView(CinemaStaff cinemaStaff) {
        this.errorMessage = "";
        this.allMovies = MovieManager.getAllMovieList(cinemaStaff);
    }

    
    public void printMenu() {
        MainView.printBoilerPlate("List of Movies");
        this.printMovieList();
    }


    public void printMovieList() {
        String content = "\nHere are the movies available:\n\n";
        int count = 0;
        for (int i = 0; i < this.allMovies.size(); i++) {
            Movie movie = allMovies.get(i);
            String index = String.format("%02d. ", (i + 1));
            String payload = String.format(index + "%s\n", movie.getMovieTitle());
            content = content + payload;
            count = i + 1;
        }
        String index = String.format("%02d. ", (count + 1));
        String payload = String.format(index + "Quit and return back\n");
        content = content + payload;

        MainView.printMenuContent(content);
    }


    public void appContent(){
        int choice = -1;

        do {
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            this.printMenu();
            choice = InputHandler.intHandler();
            if (choice < 0 || choice > this.allMovies.size() + 1) {
                this.errorMessage = "Error! Please enter a valid input!";
                continue;
            }
            if (choice == this.allMovies.size() + 1){
                this.errorMessage = "";
                return;
            }
            else {
                Movie movie = this.allMovies.get(choice - 1);
                this.staffConfigureMovieView = new StaffConfigureMovieView(movie);
                this.staffConfigureMovieView.appContent();
            }
        }   while (true);
    }
}
