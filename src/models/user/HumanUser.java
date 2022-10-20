package models.user;

public class HumanUser {
    private String name;
    private int userID;

    public HumanUser(String name, int userID) {
        this.name = name;
        this.userID = userID;
    }

    public String getUserName() {
        return this.name;
    }

    public int getUserID() {
        return this.userID;
    }
}
