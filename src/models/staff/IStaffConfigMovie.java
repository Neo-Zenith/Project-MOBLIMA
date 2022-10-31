package models.staff;
public interface IStaffConfigMovie extends IStaffConfig{
    public void updateExistingMovieDetails(String movieTitle, String movieType, int choice);
    
}