package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReminderService{
    private BorrowService borrowService;
    private MockEmailServer emailServer;

    public ReminderService(BorrowService borrowService, MockEmailServer emailServer){
        this.borrowService = borrowService;
        this.emailServer = emailServer;
    }

    public void sendOverdueReminders(Map<String, String> userEmails, LocalDate currentDate){
        for (Map.Entry<String, List<BorrowRecord>> entry : borrowService.getBorrowedBooksByUser().entrySet()){
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
        }  }}
