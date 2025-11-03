package com.example.library.service;

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
