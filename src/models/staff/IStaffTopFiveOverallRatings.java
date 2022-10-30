package models.staff;
import java.util.ArrayList;
import models.movie.Movie;

public interface IStaffTopFiveOverallRatings extends IStaffListTopFive{
    public void printTopFiveOverallRatings(ArrayList <Movie> movies);
}
