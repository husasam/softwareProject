
package com.example.library.model;

/**
 * Represents a library user.
 * Stores user ID and tracks fines for overdue items.
 *
 * Users cannot borrow new items until their fines are cleared.
 *
 * @version 1.0
 */
public class User {

    /** Unique user ID. */
    private String userId;

    /** Outstanding fine balance. */
    private double fineBalance;

    /**
     * Creates a new user.
     *
     * @param userId the unique user identifier
     */
    public User(String userId){
        this.userId = userId;
        this.fineBalance = 0.0;
    }

    /** @return user ID */
    public String getUserId(){ return userId; }

    /** @return current fine balance */
    public double getFineBalance(){ return fineBalance; }

    /**
     * Adds a fine amount to the user's balance.
     *
     * @param amount the fine amount
     */
    public void addFine(double amount){
        fineBalance += amount;
    }

    /**
     * Reduces user's fine by the given amount.
     * Fine balance cannot go negative.
     *
     * @param amount amount paid
     */
    public void payFine(double amount){
        fineBalance -= amount;
        if (fineBalance < 0) fineBalance = 0;
    }

    /**
     * Checks whether the user is eligible to borrow items.
     *
     * @return true if fine balance is zero
     */
    public boolean canBorrow(){
        return fineBalance == 0;
    }
}
