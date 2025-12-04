
package com.example.library.notifier;

import com.example.library.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Reminder service that acts as a Subject in the Observer pattern.
 * Maintains a list of observers and sends reminders to users.
 *
 * Observers may include email notifications, SMS alerts, etc.
 *
 * @author Husam
 * @version 1.0
 */
public class ReminderService {

    /** List of registered observers. */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Registers an observer to receive notifications.
     *
     * @param o the observer to register
     */
    public void register(Observer o) { 
        observers.add(o); 
    }

    /**
     * Unregisters an observer.
     *
     * @param o the observer to remove
     */
    public void unregister(Observer o) { 
        observers.remove(o); 
    }

    /**
     * Sends a reminder message to the user through all registered observers.
     *
     * @param user    the user to notify
     * @param message the reminder message
     */
    public void sendReminder(User user, String message) {
        for (Observer obs : observers) {
            obs.notify(user, message);
        }
    }
}
