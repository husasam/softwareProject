package com.example.library.strategy;

import com.example.library.model.BorrowRecord;
import com.example.library.model.Media;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Calculates overdue fines using appropriate strategy
 * depending on the media type (Book or CD).
 */
public class FineCalculator {

    private final FineStrategy bookStrategy;
    private final FineStrategy cdStrategy;

    /**
     * Creates a FineCalculator with strategies for books and CDs.
     *
     * @param bookStrategy strategy for calculating book fines
     * @param cdStrategy   strategy for calculating CD fines
     */
    public FineCalculator(FineStrategy bookStrategy, FineStrategy cdStrategy) {
        this.bookStrategy = bookStrategy;
        this.cdStrategy = cdStrategy;
    }

    /**
     * Calculates the fine for a given borrowing record.
     *
     * @param record the borrow record
     * @param now    the current date
     * @return calculated fine
     */
    public double calculate(BorrowRecord borrowRecord, LocalDate now) {
        long overdueDays = ChronoUnit.DAYS.between(borrowRecord.getDueDate(), now);
        if (overdueDays <= 0) return 0.0;

        Media m = borrowRecord.getMedia();
        if (m instanceof com.example.library.model.Book) {
            return bookStrategy.calculateFine((int) overdueDays);
        } else if (m instanceof com.example.library.model.CD) {
            return cdStrategy.calculateFine((int) overdueDays);
        } else {
            return bookStrategy.calculateFine((int) overdueDays);
        }
    }
}
