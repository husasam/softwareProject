package com.example.library.notifier;

import com.example.library.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmailNotifierTest {

    @Test
    void testNotifyDoesNotCrash() {
        EmailNotifier n = new EmailNotifier("smtp.test");
        User u = new User("u1");

        assertDoesNotThrow(() -> n.notify(u, "Hello"));
    }
}
