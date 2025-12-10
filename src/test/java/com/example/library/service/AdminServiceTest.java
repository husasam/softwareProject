package com.example.library.service;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class AdminServiceTest{

    @Test
    void testLoginSuccess(){
        AdminService adminService = new AdminService();
        assertTrue(adminService.login("admin", "1234"));
        assertTrue(adminService.isLoggedIn());
    }

    @Test
    void testLoginFail(){
        AdminService adminService = new AdminService();
        assertFalse(adminService.login("admin", "wrong"));
    }

    @Test
    void testLogout(){
        AdminService adminService = new AdminService();
        adminService.login("admin", "1234");
        adminService.logout();
        assertFalse(adminService.isLoggedIn());
    }
    @Test
    void testInvalidUserLogin(){
        AdminService adminService = new AdminService();
     //user not exist
        assertFalse(adminService.login("unknown", "1234"));
    }
}
