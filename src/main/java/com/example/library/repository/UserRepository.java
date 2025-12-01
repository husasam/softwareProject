
package com.example.library.repository;

import com.example.library.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> users = new ArrayList<>();

    public void add(User u) {
        users.add(u);
    }

    public User findById(String id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // ⭐ الحل للمشكلة:
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
