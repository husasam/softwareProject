
package com.example.library.model;

/**
 * Represents an administrator in the library system.
 * Handles authentication (login/logout) and login status tracking.
 *
 * @author Husam
 * @version 1.0
 */
public class Admin {

    /** Admin username used for login. */
    private String username;

    /** Admin password used for login. */
    private String password;

    /** Indicates whether the admin is currently logged in. */
    private boolean loggedIn;

    /**
     * Creates a new Admin with username and password.
     *
     * @param username the admin's username
     * @param password the admin's password
     */
    public Admin(String username, String password){
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    /**
     * Attempts to log in the admin.
     *
     * @param username the entered username
     * @param password the entered password
     * @return true if credentials are correct, false otherwise
     */
    public boolean login(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            this.loggedIn = true;
            return true;
        }
        return false;
    }

    /**
     * Logs out the admin.
     */
    public void logout(){
        this.loggedIn = false;
    }

    /**
     * Checks if admin is currently logged in.
     *
     * @return true if logged in, otherwise false
     */
    public boolean isLoggedIn(){
        return loggedIn;
    }
}
