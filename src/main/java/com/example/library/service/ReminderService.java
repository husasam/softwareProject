//package com.example.library.service;
//
//import com.example.library.model.Book;
//import com.example.library.model.BorrowRecord;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//public class ReminderService{
//    private BorrowService borrowService;
//    private MockEmailServer emailServer;
//
//    public ReminderService(BorrowService borrowService, MockEmailServer emailServer){
//        this.borrowService = borrowService;
//        this.emailServer = emailServer;
//    }
//
//    public void sendOverdueReminders(Map<String, String> userEmails, LocalDate currentDate){
//        for (Map.Entry<String, List<BorrowRecord>> entry : borrowService.getBorrowedBooksByUser().entrySet()){
//            String userId = entry.getKey();
//            List<BorrowRecord> userRecords = entry.getValue();
//
//            long overdueCount = userRecords.stream()
//                    .filter(r -> borrowService.isOverdue(r, currentDate))
//                    .count();
//
//            if (overdueCount > 0) {
//                String email = userEmails.get(userId);
//                String message = "You have " + overdueCount + " overdue book(s).";
//                emailServer.sendEmail(email, message);
//            }
//        }  }}
package com.example.library.service;

import com.example.library.model.BorrowRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for sending email reminders to users who have overdue books.
 */
public class ReminderService {

    private BorrowService borrowService;
    private MockEmailServer emailServer;

    /**
     * Creates a ReminderService with a borrow service and mock email server.
     *
     * @param borrowService the service used to retrieve borrowing information
     * @param emailServer   the mock email server used to send notifications
     */
    public ReminderService(BorrowService borrowService, MockEmailServer emailServer) {
        this.borrowService = borrowService;
        this.emailServer = emailServer;
    }

    /**
     * Sends overdue reminders to users who have overdue books or media items.
     *
     * @param userEmails  a map of user IDs to email addresses
     * @param currentDate the current date used to check overdue items
     */
    public void sendOverdueReminders(Map<String, String> userEmails, LocalDate currentDate) {
        for (Map.Entry<String, List<BorrowRecord>> entry :
                borrowService.getBorrowedBooksByUser().entrySet()) {

            String userId = entry.getKey();
            List<BorrowRecord> userRecords = entry.getValue();

            long overdueCount = userRecords.stream()
                    .filter(r -> borrowService.isOverdue(r, currentDate))
                    .count();

            if (overdueCount > 0) {
                String email = userEmails.get(userId);
                String message = "You have " + overdueCount + " overdue book(s).";
                emailServer.sendEmail(email, message);
            }
        }
    }
}
