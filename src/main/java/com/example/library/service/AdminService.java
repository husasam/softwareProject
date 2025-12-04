/*package com.example.library.service;

import java.util.HashMap;
import java.util.Map;

public class AdminService {
    private Map<String, String> adminCredentials = new HashMap<>();
    private boolean loggedIn = false;

    public AdminService(){
        adminCredentials.put("admin", "1234");
    }
 public boolean login(String username, String password){
        if (adminCredentials.containsKey(username) &&
            adminCredentials.get(username).equals(password)){
            loggedIn = true;
            return true;
        }
        return false; }

    public void logout(){
        loggedIn = false;
    }

    public boolean isLoggedIn(){
        return loggedIn; }
}
*/
package com.example.library.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service responsible for handling admin authentication.
 * Stores admin credentials and checks login/logout status.
 *
 * @author 
 * @version 1.0
 */
public class AdminService {

    /** Stores username-password pairs for admins. */
    private Map<String, String> adminCredentials = new HashMap<>();

    /** Tracks whether an admin is currently logged in. */
    private boolean loggedIn = false;

    /**
     * Creates a default AdminService with one admin ("admin", "1234").
     */
    public AdminService(){
        adminCredentials.put("admin", "1234");
    }

    /**
     * Attempts to login using the given username and password.
     *
     * @param username The admin username
     * @param password The admin password
     * @return true if login is successful, false otherwise
     */
    public boolean login(String username, String password){
        if (adminCredentials.containsKey(username) &&
            adminCredentials.get(username).equals(password)){
            loggedIn = true;
            return true;
        }
        return false;
    }

    /**
     * Logs out the currently logged-in admin.
     */
    public void logout(){
        loggedIn = false;
    }

    /**
     * Checks whether an admin is currently logged in.
     *
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn(){
        return loggedIn;
    }
}
