
package com.example.library.notifier;

import com.example.library.model.User;

/**
 * Email-based implementation of the {@link Observer} interface.
 * Sends notifications to users through a simulated email system.
 *
 * This class prints notifications to the console instead of sending real emails.
 *
 * @author Husam
 * @version 1.0
 */
public class EmailNotifier implements Observer {

    /** SMTP server address used for sending emails (simulated). */
    private final String smtp;

    /**
     * Creates a new EmailNotifier.
     *
     * @param smtp the SMTP server address
     */
    public EmailNotifier(String smtp) { 
        this.smtp = smtp; 
    }

    /**
     * Sends an email notification to the user.
     *
     * @param user    the recipient user
     * @param message the message to be sent
     */
    @Override
    public void notify(User user, String message) {
        System.out.println("[Email->" + user.getUserId() + "] " + message);
    }
}
