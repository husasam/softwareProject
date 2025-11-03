package com.example.library.model;

public class Admin{
    private String username;
    private String password;
    private boolean loggedIn;

    public Admin(String username, String password){
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public boolean login(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            this.loggedIn = true;
            return true;
        }
        return false;
    }

    public void logout(){
        this.loggedIn = false;
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }
}
