package com.example.library.repository;

import com.example.library.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing users in memory.
 * Provides adding, searching, and retrieving all users.
 *
 * Used by services to validate and manage user operations.
 *
 * @version 1.0
 */
public class UserRepository {

    /** Internal storage for all users. */
    private List<User> users = new ArrayList<>();

    /**
     * Adds a new user to the repository.
     *
     * @param u the user to add
     */
    public void add(User u) {
        users.add(u);
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the user ID
     * @return the matching user, or null if not found
     */
    public User findById(String id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns a list of all users in the system.
     *
     * @return list of users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
