/*
package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService{

    private List<User> users = new ArrayList<>();
    private BorrowService borrowService;
    private AdminService adminService;

    public UserService() {}
    public UserService(UserRepository repo){
        this.users = repo.getAllUsers();
    }

    public UserService(BorrowService borrowService, AdminService adminService){
        this.borrowService = borrowService;
        this.adminService = adminService;
    }

    public void registerUser(String userId){
        users.add(new User(userId));
    }

    public boolean unregisterUser(String userId){
        if (adminService == null || !adminService.isLoggedIn()){
            System.out.println("Only admin can unregister users.");
            return false;
        }

        User user = getUserById(userId);
        if (user == null) return false;

        boolean hasOverdue = borrowService != null &&
                !borrowService.getOverdueBooksByUser(userId).isEmpty();

        boolean hasFine = user.getFineBalance() > 0;

        if (hasOverdue || hasFine){
            System.out.println("Cannot unregister: User has overdue books or unpaid fines.");
            return false;
        }

        return users.remove(user);
    }

    public User getUserById(String userId){
        return users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }
}
*/
package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing library users.
 * <p>
 * Provides functionality to register, unregister, retrieve, and manage users.
 * It also interacts with {@link BorrowService} to check for overdue items 
 * and {@link AdminService} to enforce admin permissions for certain actions.
 */
public class UserService {

    /**
     * List of all registered users.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Service for handling borrowing operations.
     */
    private BorrowService borrowService;

    /**
     * Service for admin operations and permissions.
     */
    private AdminService adminService;

    /**
     * Default constructor.
     */
    public UserService() {}

    /**
     * Constructor that initializes users from a repository.
     *
     * @param repo the user repository to load users from
     */
    public UserService(UserRepository repo) {
        this.users = repo.getAllUsers();
    }

    /**
     * Constructor that initializes the service with borrow and admin services.
     *
     * @param borrowService the borrow service to check overdue books
     * @param adminService the admin service to enforce permissions
     */
    public UserService(BorrowService borrowService, AdminService adminService) {
        this.borrowService = borrowService;
        this.adminService = adminService;
    }

    /**
     * Registers a new user with the given ID.
     *
     * @param userId the ID of the user to register
     */
    public void registerUser(String userId) {
        users.add(new User(userId));
    }

    /**
     * Unregisters a user by ID.
     * <p>
     * Only an admin who is logged in can unregister users.
     * The user cannot be unregistered if they have overdue books or unpaid fines.
     *
     * @param userId the ID of the user to unregister
     * @return true if the user was successfully removed, false otherwise
     */
    public boolean unregisterUser(String userId) {
        if (adminService == null || !adminService.isLoggedIn()) {
            System.out.println("Only admin can unregister users.");
            return false;
        }

        User user = getUserById(userId);
        if (user == null) return false;

        boolean hasOverdue = borrowService != null &&
                !borrowService.getOverdueBooksByUser(userId).isEmpty();

        boolean hasFine = user.getFineBalance() > 0;

        if (hasOverdue || hasFine) {
            System.out.println("Cannot unregister: User has overdue books or unpaid fines.");
            return false;
        }

        return users.remove(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user
     * @return the User object if found, or null if not found
     */
    public User getUserById(String userId) {
        return users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns a list of all registered users.
     *
     * @return list of users
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Adds an existing user to the service.
     *
     * @param user the User object to add
     */
    public void addUser(User user) {
        users.add(user);
    }
}
