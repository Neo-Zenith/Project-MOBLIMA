package models.staff;

import java.util.ArrayList;
import models.movie.Movie;

public interface IStaffTopFiveTicketSales extends IStaffListTopFive{
    public void printTopFiveTicketSales(ArrayList <Movie> movies);
}