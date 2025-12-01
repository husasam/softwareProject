package com.example.library.notifier;

import com.example.library.model.User;


public class EmailNotifier implements Observer{
    private final String smtp;

    public EmailNotifier(String smtp) { this.smtp = smtp; }

    @Override
    public void notify(User user, String message) {
        System.out.println("[Email->" + user.getUserId() + "] " + message);
    }
}
