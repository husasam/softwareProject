package com.example.library.notifier;

import com.example.library.model.User;
import org.junit.jupiter.api.Test;

class EmailNotifierTest {

    @Test
    void testNotifyDoesNotCrash() {
        EmailNotifier n = new EmailNotifier("smtp.test");
        User u = new User("u1");

        n.notify(u, "Hello"); // just ensure no exception
    }
}
