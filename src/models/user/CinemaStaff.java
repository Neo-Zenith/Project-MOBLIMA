package models.user;

public class CinemaStaff extends HumanUser {
    private String name;
    private String password;
    private int staffID;

    public CinemaStaff(String name, int staffID, String password) {
        super(name, staffID);
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
