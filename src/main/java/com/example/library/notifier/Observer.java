package com.example.library.notifier;

import com.example.library.model.User;


 // Observer for notifications.
 
public interface Observer {
    void notify(User user, String message);
}
