package com.example.library.notifier;

import com.example.library.model.User;
import java.util.ArrayList;
import java.util.List;


 // Reminder service: Subject in Observer pattern.
 
public class ReminderService {
    private final List<Observer> observers = new ArrayList<>();

    public void register(Observer o) { observers.add(o); }
    public void unregister(Observer o) { observers.remove(o); }

    public void sendReminder(User user, String message) {
        for (Observer obs : observers) obs.notify(user, message);
    }
}
