package com.example.library.repository;

import com.example.library.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Test
    void testAddAndFind() {
        UserRepository repo = new UserRepository();
        User u = new User("u1");
        repo.add(u);

        assertEquals(u, repo.findById("u1"));
    }

    @Test
    void testGetAllUsers() {
        UserRepository repo = new UserRepository();
        repo.add(new User("1"));
        repo.add(new User("2"));
        assertEquals(2, repo.getAllUsers().size());
    }
}
