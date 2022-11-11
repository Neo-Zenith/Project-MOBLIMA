package src.model;

import java.io.Serializable;

/**
 * The class that handles the data of Cinema Staff
 * 
 * @author Jonathan Ng
 * @version 1.0
 */
public class CinemaStaff implements Serializable {

    /**
     * Unique ID of a cinema staff in the database
     */
    private String UUID;

    /**
     * Name of the cinema staff
     */
    private String name;

    /**
     * Password of the cinema staff account
     */
    private String password;

    /**
     * Username of the cinema staff account
     */
    private String username;
    private static final long serialVersionUID = 11L;

    /**
     * Constructor for the Cinema Staff class
     * 
     * @param UUID     is the unique ID in the database
     * @param name     is the name of the cinema staff
     * @param password is the password of the cinema staff's account used during
     *                 login
     * @param username is the username of the cinema staff's account used during
     *                 login
     */
    public CinemaStaff(String UUID, String name, String password, String username) {
        this.UUID = UUID;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    /**
     * Gets the unique ID of the cinema staff
     * 
     * @return Unique ID of the cinema staff
     */
    public String getUUID() {
        return this.UUID;
    }

    /**
     * Sets the unique UUID of the cinema staff
     * 
     * @param UUID is the unique ID to be set
     */

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Gets the name of the cinema staff
     * 
     * @return Name of the cinema staff
     */

    public String getName() {
        return this.name;
    }

    /**
     * Gets the password of the cinema staff account
     * 
     * @return Password of the cinema staff account
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the username of the cinema staff account
     * 
     * @return Username of the cinema staff account
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the name of the cinema staff
     * 
     * @param name is the name of the cinema staff
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the password of the cinema staff account
     * 
     * @param password is the password of the cinema staff account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * sets the username of the cinema staff account
     * 
     * @param username is the username of the cinema staff account
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
