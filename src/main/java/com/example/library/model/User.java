package com.example.library.model;

public class User{
    private String userId;
    private double fineBalance;

    public User(String userId){
        this.userId = userId;
        this.fineBalance = 0.0;
    }

    public String getUserId(){
    	return userId; }
    public double getFineBalance(){
    	return fineBalance;}

    public void addFine(double amount){
        fineBalance += amount;
    }
 public void payFine(double amount){
        fineBalance -= amount;
        if (fineBalance < 0) fineBalance = 0;
    }
 public boolean canBorrow(){
        return fineBalance == 0;
    }
}
