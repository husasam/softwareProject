package com.example.library.service;

import org.junit.jupiter.api.Test;
import com.example.library.model.User;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest{

    @Test
    void testFinePayment(){
        User user = new User("U1");
        user.addFine(50);
        assertEquals(50, user.getFineBalance());

        user.payFine(20);
        assertEquals(30, user.getFineBalance());

        user.payFine(30);
        assertEquals(0, user.getFineBalance());
        assertTrue(user.canBorrow());
    }
    @Test
    void testOverpayFine(){
        User user = new User("U2");
        user.addFine(20);
        user.payFine(50); // pay over must
        assertEquals(0, user.getFineBalance());
        assertTrue(user.canBorrow());
    }
}
