package com.example.library.service;

import java.util.HashMap;
import java.util.Map;

public class MockEmailServer {
    private Map<String, String> sentEmails = new HashMap<>();

    public void sendEmail(String to, String message) {
        sentEmails.put(to, message);
        System.out.println("Mock email sent to " + to + ": " + message);
    }

    public Map<String, String> getSentEmails() {
        return sentEmails;
    }
    public void clear() {
        sentEmails.clear();
    }
}
