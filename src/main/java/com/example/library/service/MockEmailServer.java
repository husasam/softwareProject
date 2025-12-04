//package com.example.library.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class MockEmailServer {
//    private Map<String, String> sentEmails = new HashMap<>();
//
//    public void sendEmail(String to, String message) {
//        sentEmails.put(to, message);
//        System.out.println("Mock email sent to " + to + ": " + message);
//    }
//
//    public Map<String, String> getSentEmails() {
//        return sentEmails;
//    }
//    public void clear() {
//        sentEmails.clear();
//    }
//}
package com.example.library.service;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock email server used for testing email notifications without sending real emails.
 * Stores emails in memory for verification during unit tests.
 */
public class MockEmailServer {

    /** A map storing sent emails where the key is the recipient and the value is the message. */
    private Map<String, String> sentEmails = new HashMap<>();

    /**
     * Simulates sending an email by storing it in a map.
     *
     * @param to      the email recipient
     * @param message the message being sent
     */
    public void sendEmail(String to, String message) {
        sentEmails.put(to, message);
        System.out.println("Mock email sent to " + to + ": " + message);
    }

    /**
     * Returns all sent emails.
     *
     * @return a map of email recipients and their messages
     */
    public Map<String, String> getSentEmails() {
        return sentEmails;
    }

    /**
     * Clears all stored emails, resetting the mock server.
     */
    public void clear() {
        sentEmails.clear();
    }
}
