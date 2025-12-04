
package com.example.library.notifier;

import com.example.library.model.User;

/**
 * Observer interface for notification listeners.
 * Implementations define how a notification should be delivered.
 *
 * Used in the Observer design pattern with {@link ReminderService}.
 *
 * @author Husam
 * @version 1.0
 */
public interface Observer {

    /**
     * Sends a notification to the user.
     *
     * @param user    the user receiving the notification
     * @param message the notification message
     */
    void notify(User user, String message);
}
