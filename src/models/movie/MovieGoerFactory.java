package models.movie;

public class MovieGoerFactory {

    public MovieGoer createMovieGoer(String ageGroup, int userID, String name, String email, String mobileNum,
            int age) {

        switch (ageGroup) {
            case ("Adult"):
                return new Adult(userID, name, email, mobileNum, age);
            case ("SeniorCitizen"):
                return new SeniorCitizen(userID, name, email, mobileNum, age);
            case ("Child"):
                return new Child(userID, name, email, mobileNum, age);
            default:
                return new Adult(userID, name, email, mobileNum, age);

        }
    }
}
