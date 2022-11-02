package model;

import java.io.Serializable;

public class CinemaStaff implements Serializable{
    private String UUID;
    private String name;
    private String password;
    private int staffID;
    private static final long serialVersionUID = 11L;

    public CinemaStaff(String UUID, String name, String password, int staffID){
        this.UUID = UUID;
        this.name = name;
        this.password = password;
        this.staffID = staffID;
    }
    public String getUUID() {
        return this.UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /*
     * getters and setters
     */
    
    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public int getStaffID(){
        return this.staffID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setStaffID(int staffID){
        this.staffID = staffID;
    }


}

