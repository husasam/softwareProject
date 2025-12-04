package com.example.library.notifier;

import com.example.library.model.User;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class ReminderServiceTest {

    @Test
    public void testObserverNotified() {
        ReminderService svc = new ReminderService();
        Observer obs = mock(Observer.class);
        svc.register(obs);

        User u = new User("u1");
        svc.sendReminder(u, "overdue");

        verify(obs, times(1)).notify(eq(u), eq("overdue"));
    }
}
